package com.autoinhome.autolight.view;

import com.autoinhome.autolight.entity.WxArticle;

import java.util.List;

/**
 * Created by yanglong on 2016/10/26.
 */

public interface PageView {
    void showContent(List<WxArticle> list);
    void showError();
}
