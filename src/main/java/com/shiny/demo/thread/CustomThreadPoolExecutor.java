package com.shiny.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 15:53
 * @desc 自定义线程池
 */
@Slf4j
public class CustomThreadPoolExecutor extends ThreadPoolTaskExecutor {

    private static final List<ThreadLocalFlowHandler> threadLocalFlowHandlers = new ArrayList<>();

    static {
        threadLocalFlowHandlers.add(new MdcInfoFlowHandler());
    }

    @Override
    public void execute(Runnable task) {
        Map<ThreadLocalFlowHandler, Object> parentThreadLocal = getParentThreadLocal();
        super.execute(() -> agent(task, parentThreadLocal));
    }

    @Override
    public Future<?> submit(Runnable task) {
        Map<ThreadLocalFlowHandler, Object> parentThreadLocal = getParentThreadLocal();
        return super.submit(() -> agent(task, parentThreadLocal));
    }

    private Map<ThreadLocalFlowHandler, Object> getParentThreadLocal() {
        Map<ThreadLocalFlowHandler, Object> parentThreadLocal = new HashMap<>();
        threadLocalFlowHandlers.forEach(handler -> parentThreadLocal.put(handler, handler.get()));
        return parentThreadLocal;
    }

    /**
     * 透传ThreadLocal即可
     *
     * @param task
     * @param parentThreadLocal
     */
    private void agent(Runnable task, Map<ThreadLocalFlowHandler, Object> parentThreadLocal) {
        try {
            threadLocalFlowHandlers.forEach(handler -> {
                if (parentThreadLocal.containsKey(handler)) {
                    Object obj = parentThreadLocal.get(handler);
                    handler.put(obj);
                }
            });
            task.run();
        } catch (Throwable e) {
            log.error("thread error", e);
            throw e;
        } finally {
            //清空
            threadLocalFlowHandlers.forEach(ThreadLocalFlowHandler::clear);
        }
    }

}
