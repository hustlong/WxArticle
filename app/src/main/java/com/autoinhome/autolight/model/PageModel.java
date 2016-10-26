package com.autoinhome.autolight.model;

import android.util.Log;

import com.autoinhome.autolight.entity.WxArticlePage;
import com.autoinhome.autolight.net.HttpMethods;
import com.autoinhome.autolight.presenter.PagePresenterInterface;

import rx.Subscriber;

/**
 * Created by yanglong on 2016/10/26.
 */

public class PageModel {

    private PagePresenterInterface presenterInterface;

    public PageModel(PagePresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
    }

    public void loadData(String cid, int page, int size) {
        Log.d("TAG","从服务器加载数据");
        Subscriber<WxArticlePage> subscriber = new Subscriber<WxArticlePage>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                presenterInterface.loadError();
            }

            @Override
            public void onNext(WxArticlePage wxArticlePage) {
                presenterInterface.loadContentComplete(wxArticlePage.getList());
            }
        };
        HttpMethods.getInstance().getPage(subscriber,cid,page,size);
    }
}
