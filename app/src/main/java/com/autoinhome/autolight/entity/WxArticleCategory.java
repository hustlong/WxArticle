package com.autoinhome.autolight.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yanglong on 2016/10/25.
 */

@Entity
public class WxArticleCategory {
    @Id
    private Long id;

    private String cid;

    private String name;

    @Generated(hash = 691662986)
    public WxArticleCategory(Long id, String cid, String name) {
        this.id = id;
        this.cid = cid;
        this.name = name;
    }
    @Generated(hash = 1544102014)
    public WxArticleCategory() {
    }

    public void setCid(String cid){
        this.cid = cid;
    }
    public String getCid(){
        return this.cid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
