package com.service;

import com.crawler.alibaba.AlibabaProcessor;
import com.downloader.AlibabaDownloader;
import com.entity.CrawlerTask;
import com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AliService
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
@Service
@Transactional
public class AliService {

    @Autowired
    private AlibabaDownloader alibabaDownloader;


    @Autowired
    private AlibabaProcessor alibabaProcessor;

    @Autowired
    private TaskRepository taskRepository;


    @Transactional(rollbackFor = Exception.class)
    public void crawlerStart(CrawlerTask crawlerTask) {

        CrawlerTask save = taskRepository.save(crawlerTask);

        Spider spider = Spider.create(alibabaProcessor);

        String[] keywords = crawlerTask.getKeywords();
        List<String> collect = Arrays.stream(keywords).collect(Collectors.toList());

        collect.forEach(keyword -> {
            Request request = new Request("http://www.yxbf.net/Keyword/SearchHotWords?rand=1600338591639");
            request.putExtra("taskId", save.getId());
            request.setMethod(HttpConstant.Method.POST);
            spider.addRequest(request.putExtra("page", 1).putExtra("keyword", keyword));

        });

        spider.setDownloader(alibabaDownloader);

        spider.start();
    }

}
