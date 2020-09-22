package com.crawler.alibaba;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.BasePage;
import com.entity.AliEntity;
import com.repository.AlibabaRepository;
import com.utils.RegexUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AliPage
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-17
 * @Version 1.0
 **/
@Component
public class AliPage implements BasePage {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handleUrl(String url) {
        return RegexUtil.isMatch("http://www.yxbf.net.*", url);
    }

    @Autowired
    private AlibabaRepository alibabaRepository;

    private static AliPage aliPage;

    @PostConstruct
    public void init() {
        aliPage = this;
        aliPage.alibabaRepository = this.alibabaRepository;
    }


    @Override
    public void process(Page page) {

        Long taskId = (Long) page.getRequest().getExtra("taskId");
        Integer integer = (Integer) page.getRequest().getExtra("page");
        String keyword = page.getRequest().getExtra("keyword").toString();
        String rawText = page.getRawText();
        try {
            JSONObject jsonObject = JSONObject.parseObject(rawText);
            JSONArray rows = jsonObject.getJSONArray("rows");
            List<AliEntity> aliEntities1 = rows.toJavaList(AliEntity.class);

            aliEntities1.forEach(aliEntity -> {
                aliEntity.setKeyword(keyword);
                aliEntity.setTaskId(taskId);
            });

            aliPage.alibabaRepository.saveAll(aliEntities1);

            logger.info("第{}页", integer);

            if (CollectionUtils.isNotEmpty(aliEntities1)) {

                page.addTargetRequest(new Request("http://www.yxbf.net/Keyword/SearchHotWords?rand=1600338591639").putExtra("page", integer + 1).putExtra("keyword", keyword).setMethod(HttpConstant.Method.POST).putExtra("taskId", taskId));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
