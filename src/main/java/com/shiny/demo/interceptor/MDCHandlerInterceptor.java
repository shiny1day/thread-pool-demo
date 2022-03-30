package com.shiny.demo.interceptor;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.shiny.demo.constant.CommonConstant.COMMON_HEADER_TRACE_ID;
import static com.shiny.demo.constant.CommonConstant.COMMON_TRACE_ID;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 14:01
 * @desc 日志添加请求id
 */
public class MDCHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(COMMON_HEADER_TRACE_ID);
        if (!StringUtils.hasText(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        response.addHeader(COMMON_HEADER_TRACE_ID, traceId);
        MDC.put(COMMON_TRACE_ID, traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(COMMON_TRACE_ID);
    }

}