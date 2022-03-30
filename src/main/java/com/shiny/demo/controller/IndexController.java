package com.shiny.demo.controller;

import com.shiny.demo.service.IndexService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 13:57
 * @desc
 */
@RestController
public class IndexController {

    @Resource
    private IndexService indexService;

    @RequestMapping("/login")
    public String index() {
        indexService.cycle();
        return "OK!";
    }

}
