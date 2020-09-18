package com.crawler.alibaba;

import com.base.BasePageProcessor;
import com.crawler.alibaba.AliPage;
import com.downloader.DynamicProxyDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;

/**
 * @ClassName AlibabaProcessor
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
@Component
public class AlibabaProcessor extends BasePageProcessor {


    private Site site = Site.me()
            .setCharset("utf-8")
            .setRetryTimes(6)//重试6次
            .setSleepTime(5000)
            .setTimeOut(10 * 1000)
//            .addHeader("Proxy-Authorization", DynamicProxyDownloader.getAuthHeader())
//            .addHeader("Referer", "https://s.weibo.com")
            .addHeader("Cookie", randomCookie())
            .addHeader("User-Agent", randomUserAgent());

    public AlibabaProcessor(){
        super(new AliPage());
    }

    @Override
    public Site getSite() {
        return site;
    }

}
