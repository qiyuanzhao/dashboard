package com.repository;

import com.entity.CrawlerTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName TaskRepository
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
public interface TaskRepository extends JpaRepository<CrawlerTask,Long> {
}
