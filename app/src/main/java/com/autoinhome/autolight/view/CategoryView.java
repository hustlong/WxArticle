package com.autoinhome.autolight.view;

import com.autoinhome.autolight.entity.WxArticleCategory;

import java.util.List;

/**
 * Created by yanglong on 2016/10/25.
 */

public interface CategoryView {
    void showCategory(List<WxArticleCategory> categories);
    void showError();
}
