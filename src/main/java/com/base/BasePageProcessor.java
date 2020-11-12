package com.base;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class BasePageProcessor implements PageProcessor {

    private BasePage[] basePages;

    private static String[] USER_AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36", // chrome
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5943.400 SLBrowser/10.0.3365.400", // 联想浏览器
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0", //火狐
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko" //ie
    };

    public static String randomUserAgent() {
        int user_agent_index = (int) (Math.random() * (USER_AGENTS.length));
        return USER_AGENTS[user_agent_index];
    }

    public BasePageProcessor(BasePage... basePages) {
        if (basePages == null || basePages.length == 0) {
            throw new IllegalArgumentException("com.processor parse size is 0");
        }
        this.basePages = basePages;
    }


    @Override
    public void process(Page page) {
        for (BasePage basePage : basePages) {
            if (basePages == null) {
                throw new RuntimeException("com.processor parse is null");
            }
            if (basePage.handleUrl(page.getUrl().get())) {
                basePage.process(page);
            }
        }
    }

    private static String[] COOKIE = new String[]{
//            "SINAGLOBAL=9262762579760.895.1542259856140; UOR=,,login.sina.com.cn; un=tig6tz9g@duoduo.cafe; _s_tentry=s.weibo.com; Apache=623447599934.0061.1569154423470; ULV=1569154666807:2:2:1:623447599934.0061.1569154423470:1568629118094; login_sid_t=d38b1d5c23723d850528c9226101bd20; cross_origin_proto=SSL; wvr=6; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWeSACZFWFB7H-smC59bknG5JpX5K2hUgL.FoM0ehMNSh.XeKB2dJLoIERLxK-L12-L1h9ki--ci-zfi-8Wi--ciKn7i-2Ni--4iKLWi-i8i--ciK.Xi-z4; ALF=1600925438; SSOLoginState=1569389438; SCF=AtgnXFBDvRA-ACAFTJbJ_U2hd-M9-tcEg6lbqA47ypoChqp_2QBs-N2187fnz0GM2FjFj_cPR9UC5gtaIVanpmU.; SUB=_2A25wjovRDeRhGeFN61UW9CfIyjiIHXVT_foZrDV8PUNbmtAKLW6skW9NQGl_D173ePEtpKmfWtrjGSBvYlhzmxrD; SUHB=0cS32hun2ucN28; un=357zi6ni@anjing.cool; webim_unReadCount=%7B%22time%22%3A1569389444448%2C%22dm_pub_total%22%3A0%2C%22chat_group_client%22%3A0%2C%22allcountNum%22%3A23%2C%22msgbox%22%3A0%7D",
            "ASP.NET_SessionId=ton3bpymqwdt5555z0aep3tp; Hm_lvt_6ad0ac34cce0dc99467a710197680252=1605095141; Hm_lpvt_6ad0ac34cce0dc99467a710197680252=1605095342",
    };

    public static String randomCookie() {
        int user_agent_index = (int) (Math.random() * (COOKIE.length));
        return COOKIE[user_agent_index];
    }

}
