package com.autoinhome.autolight.entity;

import java.util.List;

/**
 * Created by yanglong on 2016/10/25.
 */

public class WxArticlePage {
    private int curPage;

    private List<WxArticle> list ;

    private int total;

    public void setCurPage(int curPage){
        this.curPage = curPage;
    }
    public int getCurPage(){
        return this.curPage;
    }

    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }

    public List<WxArticle> getList() {
        return list;
    }

    public void setList(List<WxArticle> list) {
        this.list = list;
    }
}
