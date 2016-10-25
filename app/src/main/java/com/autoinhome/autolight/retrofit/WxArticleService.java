package com.autoinhome.autolight.retrofit;

import com.autoinhome.autolight.entity.HttpResult;
import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.entity.WxArticlePage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yanglong on 2016/10/25.
 */

public interface WxArticleService {
    @GET("wx/article/category/query")
    Observable<HttpResult<List<WxArticleCategory>>> getCategory(@Query("key") String key);

    @GET("wx/article/search")
    Observable<HttpResult<WxArticlePage>> getPage(
            @Query("key") String key,
            @Query("cid") String cid,
            @Query("page") String page,
            @Query("size") String size);
}
