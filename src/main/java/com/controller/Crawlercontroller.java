package com.controller;

import com.entity.CrawlerTask;
import com.service.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName Crawlercontroller
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
@RestController
@RequestMapping("/crawler")
public class Crawlercontroller {


    @Autowired
    private AliService aliService;


    @PostMapping
    public ResponseEntity uploadFile(@RequestBody @Valid CrawlerTask crawlerTask) {
        try {
            aliService.crawlerStart(crawlerTask);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }


}
