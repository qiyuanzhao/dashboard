package com.controller;

import com.entity.CrawlerTask;
import com.monitor.MySpiderMonitor;
import com.monitor.MySpiderStatus;
import com.service.AliService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
@Api("爬取相关操作")
public class Crawlercontroller {


    @Autowired
    private AliService aliService;


    @ApiOperation("启动爬虫")
    @PostMapping
    public ResponseEntity uploadFile(@RequestBody @Valid CrawlerTask crawlerTask) {
        try {
            ArrayList<String> strings = get();
            String[] strings1 = strings.toArray(new String[strings.size()]);
            crawlerTask.setKeywords(strings1);
            aliService.crawlerStart(crawlerTask);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }


    private ArrayList<String> get(){

        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("/Users/qiyuanzhao/Desktop/cloumns.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * 爬虫监控  stopped ： 爬虫停止状态   running ：爬虫运行状态
     * @return
     */
    @GetMapping
    @ApiOperation("爬虫监控")
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
