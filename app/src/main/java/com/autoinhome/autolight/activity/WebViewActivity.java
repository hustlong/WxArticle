package com.autoinhome.autolight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.autoinhome.autolight.R;
import com.autoinhome.autolight.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglong on 2016/10/26.
 */

public class WebViewActivity extends BaseActivity {

    String title;
    String URL;

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.root)
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initData();
        initViews();
        getContent();
    }

    private void getContent() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                avi.hide();
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        root.removeView(webView);
        webView.destroy();
    }

    private void initViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    private void initData() {
        Intent it = getIntent();
        if (it != null) {
            title = it.getStringExtra("title");
            URL = it.getStringExtra("URL");
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
