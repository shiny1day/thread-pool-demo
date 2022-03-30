package com.shiny.demo.thread;

import org.slf4j.MDC;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 16:27
 * @Copyright Copyright(c) 2021 xintech Inc.All Rights Reserved.
 * @desc MDC日志数据透传
 */
public class MdcInfoFlowHandler implements ThreadLocalFlowHandler {

    @Override
    public void put(Object obj) {
        // MDC 透传
        if (!ObjectUtils.isEmpty(obj)) {
            MDC.setContextMap((Map<String, String>) obj);
        }
    }

    @Override
    public Object get() {
        return MDC.getCopyOfContextMap();
    }

    @Override
    public void clear() {
        MDC.clear();
    }

}
