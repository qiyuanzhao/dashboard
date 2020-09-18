package com.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.entity.base.Auditable;

import javax.persistence.Entity;

/**
 * @ClassName AliEntity
 * @Description 阿里实体
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
@Entity
public class AliEntity extends Auditable {

    private Long taskId;

    @ExcelProperty(value = "爬取关键词",index = 0)
    private String keyword;

    @ExcelProperty(value = "关键词",index = 1)
    private String word;

    @ExcelProperty(value = "橱窗数",index = 2)
    private String showwinCnt;

    @ExcelProperty(value = "买家竞争",index = 3)
    private String companyCnt;

    @ExcelProperty(value = "本月搜索热度",index = 4)
    private String srhPvThisMon;

    @ExcelProperty(value = "最近1个月",index = 5)
    private String srhPvLast1mon;

    @ExcelProperty(value = "最近2个月",index = 6)
    private String srhPvLast2mon;

    @ExcelProperty(value = "最近3个月",index = 7)
    private String srhPvLast3mon;

    @ExcelProperty(value = "最近4个月",index = 8)
    private String srhPvLast4mon;

    @ExcelProperty(value = "最近5个月",index = 9)
    private String srhPvLast5mon;

    @ExcelProperty(value = "最近6个月",index = 10)
    private String srhPvLast6mon;

    @ExcelProperty(value = "最近7个月",index = 11)
    private String srhPvLast7mon;

    @ExcelProperty(value = "最近8个月",index = 12)
    private String srhPvLast8mon;

    @ExcelProperty(value = "最近9个月",index = 13)
    private String srhPvLast9mon;

    @ExcelProperty(value = "最近10个月",index = 14)
    private String srhPvLast10mon;

    @ExcelProperty(value = "最近11个月",index = 15)
    private String srhPvLast11mon;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getShowwinCnt() {
        return showwinCnt;
    }

    public void setShowwinCnt(String showwinCnt) {
        this.showwinCnt = showwinCnt;
    }

    public String getCompanyCnt() {
        return companyCnt;
    }

    public void setCompanyCnt(String companyCnt) {
        this.companyCnt = companyCnt;
    }

    public String getSrhPvThisMon() {
        return srhPvThisMon;
    }

    public void setSrhPvThisMon(String srhPvThisMon) {
        this.srhPvThisMon = srhPvThisMon;
    }

    public String getSrhPvLast10mon() {
        return srhPvLast10mon;
    }

    public void setSrhPvLast10mon(String srhPvLast10mon) {
        this.srhPvLast10mon = srhPvLast10mon;
    }

    public String getSrhPvLast11mon() {
        return srhPvLast11mon;
    }

    public void setSrhPvLast11mon(String srhPvLast11mon) {
        this.srhPvLast11mon = srhPvLast11mon;
    }

    public String getSrhPvLast1mon() {
        return srhPvLast1mon;
    }

    public void setSrhPvLast1mon(String srhPvLast1mon) {
        this.srhPvLast1mon = srhPvLast1mon;
    }

    public String getSrhPvLast2mon() {
        return srhPvLast2mon;
    }

    public void setSrhPvLast2mon(String srhPvLast2mon) {
        this.srhPvLast2mon = srhPvLast2mon;
    }

    public String getSrhPvLast3mon() {
        return srhPvLast3mon;
    }

    public void setSrhPvLast3mon(String srhPvLast3mon) {
        this.srhPvLast3mon = srhPvLast3mon;
    }

    public String getSrhPvLast4mon() {
        return srhPvLast4mon;
    }

    public void setSrhPvLast4mon(String srhPvLast4mon) {
        this.srhPvLast4mon = srhPvLast4mon;
    }

    public String getSrhPvLast5mon() {
        return srhPvLast5mon;
    }

    public void setSrhPvLast5mon(String srhPvLast5mon) {
        this.srhPvLast5mon = srhPvLast5mon;
    }

    public String getSrhPvLast6mon() {
        return srhPvLast6mon;
    }

    public void setSrhPvLast6mon(String srhPvLast6mon) {
        this.srhPvLast6mon = srhPvLast6mon;
    }

    public String getSrhPvLast7mon() {
        return srhPvLast7mon;
    }

    public void setSrhPvLast7mon(String srhPvLast7mon) {
        this.srhPvLast7mon = srhPvLast7mon;
    }

    public String getSrhPvLast8mon() {
        return srhPvLast8mon;
    }

    public void setSrhPvLast8mon(String srhPvLast8mon) {
        this.srhPvLast8mon = srhPvLast8mon;
    }

    public String getSrhPvLast9mon() {
        return srhPvLast9mon;
    }

    public void setSrhPvLast9mon(String srhPvLast9mon) {
        this.srhPvLast9mon = srhPvLast9mon;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
