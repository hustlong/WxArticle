package com.autoinhome.autolight.presenter;

import com.autoinhome.autolight.entity.WxArticleCategory;

import java.util.List;

/**
 * Created by yanglong on 2016/10/25.
 */

public interface CategoryPresenterInterface {

    void loadCategoryComplete(List<WxArticleCategory> categories);

    void loadError();
}
