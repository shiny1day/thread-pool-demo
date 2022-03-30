package com.shiny.demo.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 15:55
 * @desc 线程池配置
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * cpu内核 暂时默认8核
     */
    private static final int CORE_SIZE = 8;

    /**
     * 核心线程数 暂定为I/O密集型
     */
    private static final int CORE_POOL_SIZE = 2 * CORE_SIZE + 1;

    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 4 * CORE_SIZE + 1;

    /**
     * 线程队列容量
     */
    private static final int QUEUE_CAPACITY = 1000;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        //看情况决定是否自定义线程池
        CustomThreadPoolExecutor threadPoolTaskExecutor = new CustomThreadPoolExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.setThreadNamePrefix("shiny-thread-");
        //交由调用方线程运行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setDaemon(true);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
