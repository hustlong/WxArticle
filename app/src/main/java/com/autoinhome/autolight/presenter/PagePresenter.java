package com.autoinhome.autolight.presenter;

import com.autoinhome.autolight.base.BasePresenter;
import com.autoinhome.autolight.entity.WxArticle;
import com.autoinhome.autolight.model.PageModel;
import com.autoinhome.autolight.view.PageView;

import java.util.List;

/**
 * Created by yanglong on 2016/10/26.
 */

public class PagePresenter implements BasePresenter<PageView>,PagePresenterInterface {

    private PageView view;
    private PageModel model;

    public PagePresenter(PageView view) {
        attachView(view);
        model = new PageModel(this);
    }

    public void loadData(String cid,int page,int size) {
        model.loadData(cid,page,size);
    }


    @Override
    public void attachView(PageView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadContentComplete(List<WxArticle> list) {
        view.showContent(list);
    }

    @Override
    public void loadError() {
        view.showError();
    }
}
