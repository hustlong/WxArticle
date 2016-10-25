package com.autoinhome.autolight.entity;

/**
 * Created by yanglong on 2016/10/25.
 */

public class WxArticle {
    private String cid;

    private String id;

    private String pubTime;

    private String sourceUrl;

    private String subTitle;

    private String title;

    public void setCid(String cid){
        this.cid = cid;
    }
    public String getCid(){
        return this.cid;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setPubTime(String pubTime){
        this.pubTime = pubTime;
    }
    public String getPubTime(){
        return this.pubTime;
    }
    public void setSourceUrl(String sourceUrl){
        this.sourceUrl = sourceUrl;
    }
    public String getSourceUrl(){
        return this.sourceUrl;
    }
    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }
    public String getSubTitle(){
        return this.subTitle;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}
