package com.autoinhome.autolight.base;

/**
 * Created by code on 16/7/22.
 */
public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();
}
