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
    private String[] keywords;

    @Column(nullable = false, name = "keywords")
    private String keywordsConcat;


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

    public String getKeywordsConcat() {
        return keywordsConcat;
    }

    public void setKeywordsConcat(String keywordsConcat) {
        this.keywordsConcat = keywordsConcat;
    }


}
