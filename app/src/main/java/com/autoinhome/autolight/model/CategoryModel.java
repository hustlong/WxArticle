package com.autoinhome.autolight.model;

import android.util.Log;

import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.greendao.GreenDaoManager;
import com.autoinhome.autolight.greendao.gen.WxArticleCategoryDao;
import com.autoinhome.autolight.net.HttpMethods;
import com.autoinhome.autolight.presenter.CategoryPresenterInterface;
import com.autoinhome.autolight.util.EmptyUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yanglong on 2016/10/25.
 */

public class CategoryModel {

    private CategoryPresenterInterface presenterInterface;

    public CategoryModel(CategoryPresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
    }


    /**
     * 先从本地加载目录，判断，为空就从服务器加载。
     */
    public void loadCategoryFromLocal() {
        Observable.create(new Observable.OnSubscribe<List<WxArticleCategory>>() {
            @Override
            public void call(Subscriber<? super List<WxArticleCategory>> subscriber) {
                WxArticleCategoryDao dao= GreenDaoManager
                        .getInstance()
                        .getSession()
                        .getWxArticleCategoryDao();

                List<WxArticleCategory> categories = dao.queryBuilder()
                        .where(WxArticleCategoryDao.Properties.Id.isNotNull())
                        .list();
//                try {
//                    Thread.sleep(1500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                subscriber.onNext(categories);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<WxArticleCategory>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(List<WxArticleCategory> wxArticleCategories) {
                        if (EmptyUtil.isEmpty(wxArticleCategories))
                            loadCategoryFromServer();
                        else
                            presenterInterface.loadCategoryComplete(wxArticleCategories);
                    }
                });
    }

    /**
     * 从服务器上加载目录
     */
    private void loadCategoryFromServer() {
        Log.d("TAG","从服务器加载数据");
        Subscriber<List<WxArticleCategory>> subscriber = new Subscriber<List<WxArticleCategory>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                presenterInterface.loadError();
            }

            @Override
            public void onNext(final List<WxArticleCategory> wxArticleCategories) {
                /** 单独开线程写数据 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WxArticleCategoryDao dao= GreenDaoManager
                                .getInstance()
                                .getSession()
                                .getWxArticleCategoryDao();
                        for (WxArticleCategory category : wxArticleCategories)
                            dao.insert(category);
                    }
                }).start();
                /** 通知 presenter 加载完成 */
                presenterInterface.loadCategoryComplete(wxArticleCategories);
            }
        };
        HttpMethods.getInstance().getCategory(subscriber);
    }
}
