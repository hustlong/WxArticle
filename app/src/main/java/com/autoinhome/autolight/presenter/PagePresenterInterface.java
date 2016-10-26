package com.autoinhome.autolight.presenter;

import com.autoinhome.autolight.entity.WxArticle;

import java.util.List;

/**
 * Created by yanglong on 2016/10/26.
 */

public interface PagePresenterInterface {
    void loadContentComplete(List<WxArticle> list);
    void loadError();
}
