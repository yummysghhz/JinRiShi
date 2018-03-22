package com.app.zilla.jinrishi.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.app.zilla.jinrishi.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class NewsDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //@BindView(R.id.collapsing_toolbar)
    private CollapsingToolbarLayout collapsingToolbar;
    //@BindView(R.id.web_text)
    private WebView webText;
    //@BindView(R.id.ivImage)
    private ImageView ivImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        webText=(WebView)findViewById(R.id.web_text);
        ivImage=(ImageView)findViewById(R.id.ivImage);

        toolbar.setTitle("新闻详情");
        setSupportActionBar(toolbar);
//        设置返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        final ArrayList<String> data = bundle.getStringArrayList("data");
        Log.d("url", data.get(0));

        webText.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        webText.loadUrl(data.get(1));

        Glide.with(this)
                .load(data.get(0)).error(R.mipmap.ic_launcher)
                .fitCenter().into(ivImage);
    }
}
