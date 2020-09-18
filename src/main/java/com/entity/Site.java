package com.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Yuanzhao Qi
 */
public enum Site {
    /**
     *
     */
    WEIBO("阿里", "ali");

    private String name;
    private String code;


    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public static Site findByCode(String code) {
        for (Site site : Site.values()) {
            if (site.getCode().equals(code)) {
                return site;
            }
        }
        return null;
    }

    @JsonCreator
    Site(String name, String code) {
        this.name = name;
        this.code = code;
    }

}
