package com.autoinhome.autolight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.autoinhome.autolight.R;
import com.autoinhome.autolight.adapter.PageAdapter;
import com.autoinhome.autolight.base.BaseActivity;
import com.autoinhome.autolight.entity.WxArticle;
import com.autoinhome.autolight.presenter.PagePresenter;
import com.autoinhome.autolight.view.PageView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglong on 2016/10/26.
 */

public class PageActivity extends BaseActivity implements PageView,PageAdapter.OnItemClickListener {

    @BindView(R.id.avi)
    AVLoadingIndicatorView loading;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    PagePresenter presenter;
    PageAdapter adapter;

    String cid;
    String name;

    static int SIZE = 15;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ButterKnife.bind(this);
        initData();

        presenter = new PagePresenter(this);
        adapter = new PageAdapter(this,this);
        initViews();
    }

    private void initData() {
        Intent it = getIntent();
        if (it != null) {
            cid = it.getStringExtra("cid");
            name = it.getStringExtra("name");
        } else {
            finish();
        }
    }

    private void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new PageAdapter.ItemDecoration(16));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name);
        }
    }

    private void refreshTitle() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setSubtitle("第"+page+"页");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadData(cid,page,SIZE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.page_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showContent(List<WxArticle> list) {
        adapter.setData(list);
        refreshTitle();
        recyclerView.smoothScrollToPosition(0);
        loading.hide();
    }

    @Override
    public void showError() {
        Toast.makeText(this,"没有了",Toast.LENGTH_SHORT).show();
        loading.hide();
    }

    @Override
    public void onClick(int pos, WxArticle article) {
        Log.d("TAG",article.getTitle());

        /** 过滤不良信息*/
        if (article.getCid().equals("24")) {
            Toast.makeText(this,"18禁",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent it = new Intent(this,WebViewActivity.class);
        it.putExtra("title",article.getTitle());
        it.putExtra("URL",article.getSourceUrl());
        startActivity(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.last:
                if (page==1)
                    showError();
                else {
                    page--;
                    loading.show();
                    presenter.loadData(cid,page,SIZE);
                }
                break;
            case R.id.next:
                page++;
                loading.show();
                presenter.loadData(cid,page,SIZE);
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
