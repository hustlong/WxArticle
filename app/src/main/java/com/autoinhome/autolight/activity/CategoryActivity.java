package com.autoinhome.autolight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.autoinhome.autolight.R;
import com.autoinhome.autolight.adapter.CategoryAdapter;
import com.autoinhome.autolight.base.BaseActivity;
import com.autoinhome.autolight.entity.WxArticleCategory;
import com.autoinhome.autolight.presenter.CategoryPresenter;
import com.autoinhome.autolight.view.CategoryView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity implements CategoryView,CategoryAdapter.OnItemClickListener {

    @BindView(R.id.avi)
    AVLoadingIndicatorView loading;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    CategoryPresenter presenter;

    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        presenter = new CategoryPresenter(this);
        adapter = new CategoryAdapter(this,this);
        initViews();
    }

    private void initViews() {
        GridLayoutManager manager = new GridLayoutManager(this,4);
        recyclerView.addItemDecoration(new CategoryAdapter.ItemDecoration(16,4));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadCategory();
    }

    @Override
    public void showCategory(List<WxArticleCategory> categories) {
        adapter.setData(categories);
        loading.hide();
    }

    @Override
    public void showError() {
        Toast.makeText(this,"没有了",Toast.LENGTH_SHORT).show();
        loading.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onClick(int pos, WxArticleCategory category) {
        Intent it = new Intent(this,PageActivity.class);
        it.putExtra("cid",category.getCid());
        it.putExtra("name",category.getName());
        startActivity(it);
    }
}
