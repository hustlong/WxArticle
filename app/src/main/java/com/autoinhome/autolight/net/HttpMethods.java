package com.autoinhome.autolight.net;

import com.autoinhome.autolight.entity.HttpResult;
import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.entity.WxArticlePage;
import com.autoinhome.autolight.retrofit.WxArticleService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yanglong on 2016/10/25.
 */

public class HttpMethods {
    private static final String BASE_URL = "http://apicloud.mob.com";
    private static final String KEY = "10193c61f77c8";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private WxArticleService wxArticleService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        wxArticleService = retrofit.create(WxArticleService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


    /**
     * 用于获取目录
     * @param subscriber 由调用者传过来的观察者对象
     */
    public void getCategory(Subscriber<List<WxArticleCategory>> subscriber) {
        wxArticleService.getCategory(KEY)
                .map(new HttpResultFunc<List<WxArticleCategory>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用于获取内容
     * @param subscriber 由调用者传过来的观察者对象
     * @param cid 类别
     * @param page 当前页码
     * @param size 内容数量
     */
    public void getPage(Subscriber<WxArticlePage> subscriber,String cid,String page,String size) {
        wxArticleService.getPage(KEY,cid,page,size)
                .map(new HttpResultFunc<WxArticlePage>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 用来统一处理 Http 的 retCode,并将 HttpResult 的 result 部分剥离出来返回给 subscriber
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) throws ApiException{
            if (!httpResult.getRetCode().equals("200")) {
                throw new ApiException(httpResult.getRetCode());
            }
            return httpResult.getResult();
        }
    }

}
