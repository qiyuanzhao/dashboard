package com.controller;

import com.entity.CrawlerTask;
import com.monitor.MySpiderMonitor;
import com.monitor.MySpiderStatus;
import com.service.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping
    public ResponseEntity getStatus() {

        Map<String, String> map = new HashMap<>();
        MySpiderMonitor mySpiderMonitor = aliService.getMySpiderMonitor();

        if (mySpiderMonitor == null) {
            map.put("data", "Stopped");
        } else {
            List<MySpiderStatus> spiderStatuses = mySpiderMonitor.getSpiderStatuses();
            MySpiderStatus mySpiderStatus = spiderStatuses.get(0);
            String status = mySpiderStatus.getStatus();
            map.put("data", status);
        }
        return ResponseEntity.ok(map);

    }


}
