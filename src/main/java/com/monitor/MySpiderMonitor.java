package com.monitor;

/**
 * @ClassName MySpiderMonitor
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-22
 * @Version 1.0
 **/

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.JMException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;
import us.codecraft.webmagic.utils.Experimental;

@Experimental
public class MySpiderMonitor {

    private AtomicBoolean started = new AtomicBoolean(false);

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MBeanServer mbeanServer;

    private String jmxServerName;

    private List<MySpiderStatus> spiderStatuses = new ArrayList<>();

    protected MySpiderMonitor() {
        jmxServerName = "WebMagic";
        mbeanServer = ManagementFactory.getPlatformMBeanServer();
    }
    public List<MySpiderStatus> getSpiderStatuses()
    {
        return spiderStatuses;
    }

    /**
     * Register spider for monitor.
     *
     * @param spiders spiders
     * @return this
     */
    public synchronized MySpiderMonitor register(Spider... spiders) throws JMException {
        for (Spider spider : spiders) {
            MyMonitorSpiderListener monitorSpiderListener = new MyMonitorSpiderListener();
            if (spider.getSpiderListeners() == null) {
                List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
                spiderListeners.add(monitorSpiderListener);
                spider.setSpiderListeners(spiderListeners);
            } else {
                spider.getSpiderListeners().add(monitorSpiderListener);
            }
            MySpiderStatus spiderStatusMBean = getSpiderStatusMBean(spider, monitorSpiderListener);
            registerMBean(spiderStatusMBean);
            spiderStatuses.add(spiderStatusMBean);
        }
        return this;
    }

    protected MySpiderStatus getSpiderStatusMBean(Spider spider, MyMonitorSpiderListener monitorSpiderListener) {
        return new MySpiderStatus(spider, monitorSpiderListener);
    }

    public static MySpiderMonitor instance() {
        return new MySpiderMonitor();
    }

    public class MyMonitorSpiderListener implements SpiderListener {

        private final AtomicInteger successCount = new AtomicInteger(0);

        private final AtomicInteger errorCount = new AtomicInteger(0);

        private List<String> errorUrls = Collections.synchronizedList(new ArrayList<String>());

        @Override
        public void onSuccess(Request request) {
            successCount.incrementAndGet();
        }

        @Override
        public void onError(Request request) {
            errorUrls.add(request.getUrl());
            errorCount.incrementAndGet();
        }

        public AtomicInteger getSuccessCount() {
            return successCount;
        }

        public AtomicInteger getErrorCount() {
            return errorCount;
        }

        public List<String> getErrorUrls() {
            return errorUrls;
        }
    }

    protected void registerMBean(SpiderStatusMXBean spiderStatus) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        ObjectName objName = new ObjectName(jmxServerName + ":name=" + spiderStatus.getName());
        if(mbeanServer.isRegistered(objName)==false)
        {
            mbeanServer.registerMBean(spiderStatus, objName);
        }
    }
}
