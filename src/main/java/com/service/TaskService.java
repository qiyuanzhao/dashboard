package com.service;

import com.entity.CrawlerTask;
import com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TaskService
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-22
 * @Version 1.0
 **/
@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;


    public CrawlerTask findOne(Long taskId) {
        return taskRepository.findById(taskId).orElse(new CrawlerTask());
    }

    public List<CrawlerTask> findAll() {
        return taskRepository.findAll();
    }

    public void deleteTaskById(Long taskId) {

        taskRepository.deleteById(taskId);

    }

    public CrawlerTask saveTask(CrawlerTask crawlerTask) {

        return taskRepository.save(crawlerTask);


    }
}
