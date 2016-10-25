package com.autoinhome.autolight.activity;

import android.os.Bundle;
import android.util.Log;

import com.autoinhome.autolight.R;
import com.autoinhome.autolight.base.BaseActivity;
import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.net.HttpMethods;
import com.autoinhome.autolight.presenter.CategoryPresenter;
import com.autoinhome.autolight.view.CategoryView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class CategoryActivity extends BaseActivity implements CategoryView{

    @BindView(R.id.avi)
    AVLoadingIndicatorView loading;
    CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new CategoryPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadCategory();
    }

    @Override
    public void showCategory(List<WxArticleCategory> categories) {
        for (WxArticleCategory category : categories)
            Log.d("TAG",category.getCid() + ":" + category.getName());
        loading.hide();
    }

    @Override
    public void showError() {
        Log.d("TAG","Error");
        loading.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
