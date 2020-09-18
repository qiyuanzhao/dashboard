package com.entity;

import com.entity.base.Auditable;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class CrawlerTask extends Auditable {

    @Transient
    @NotNull
    private String[] sites;

    @Transient
    @NotNull
    private String[] keywords;

    private String fileUrl;

    @Column(nullable = false, name = "site")
    private String siteConcat;

    @Column(nullable = false, name = "keywords")
    private String keywordsConcat;


    public String[] getSites() {
        if (this.sites == null || this.sites.length == 0) {
            if (StringUtils.isNoneBlank(this.siteConcat)) {
                this.sites = StringUtils.split(this.siteConcat, ",");
            } else {
                this.sites = new String[0];
            }
        }
        return sites;
    }

    public void setSites(String... sites) {
        sites = StringUtils.stripAll(sites);
        this.sites = sites;
        this.siteConcat = StringUtils.join(sites, ",");
    }

    public String[] getKeywords() {
        if (this.keywords == null || this.keywords.length == 0) {
            if (StringUtils.isNoneBlank(this.keywordsConcat)) {
                this.keywords = StringUtils.split(this.keywordsConcat, ",");
            } else {
                this.keywords = new String[0];
            }
        }
        return keywords;
    }

    public void setKeywords(String... keywords) {
        keywords = StringUtils.stripAll(keywords);
        this.keywords = keywords;
        this.keywordsConcat = StringUtils.join(keywords, ",");
    }


    public String getSiteConcat() {
        return siteConcat;
    }

    public void setSiteConcat(String siteConcat) {
        this.siteConcat = siteConcat;
    }


    public String getKeywordsConcat() {
        return keywordsConcat;
    }

    public void setKeywordsConcat(String keywordsConcat) {
        this.keywordsConcat = keywordsConcat;
    }


}
