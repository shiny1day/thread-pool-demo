package com.shiny.demo.thread;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 16:24
 * @desc threadLocal 父子线程透传处理器
 */
public interface ThreadLocalFlowHandler {

    /**
     * 放入threadLocal
     * @param obj
     */
    void put(Object obj);

    /**
     * 取出数据
     * @return
     */
    Object get();

    /**
     * 清除数据
     */
    void clear();

}
