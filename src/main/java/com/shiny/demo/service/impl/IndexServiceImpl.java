package com.shiny.demo.service.impl;

import com.shiny.demo.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 16:08
 * @Copyright Copyright(c) 2021 xintech Inc.All Rights Reserved.
 * @desc
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * @return void
     * @desc 循环执行任务
     * @author dengkongze
     * @date 2022/3/30 16:09
     */
    @Override
    public void cycle() {
        log.info("开始执行任务!");
        for (int i = 0; i < 20; i++) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    Thread.sleep(5000);
                    //执行任务
                    log.info("执行完毕!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
