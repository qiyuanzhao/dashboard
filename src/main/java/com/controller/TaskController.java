package com.controller;

import com.entity.CrawlerTask;
import com.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TaskController
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-22
 * @Version 1.0
 **/
@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private TaskService taskService;


    @GetMapping("/{taskId}")
    public ResponseEntity findOne(@PathVariable("taskId") Long taskId) {

        CrawlerTask crawlerTask = taskService.findOne(taskId);

        return ResponseEntity.ok(crawlerTask);
    }


    @GetMapping
    public ResponseEntity findAll() {

        List<CrawlerTask> crawlerTaskList = taskService.findAll();

        return ResponseEntity.ok(crawlerTaskList);
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody CrawlerTask crawlerTask){

        CrawlerTask newCrawlerTask = taskService.saveTask(crawlerTask);

        return ResponseEntity.ok(newCrawlerTask);

    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTaskById(@PathVariable("taskId") Long taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }


}
