
package com.app.zilla.jinrishi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.activity.NewsDetailsActivity;
import com.app.zilla.jinrishi.constants.AppInfo;
import com.app.zilla.jinrishi.fragment.tips_fragment.Easyrecycle.NewsAdapter;
import com.app.zilla.jinrishi.fragment.tips_fragment.html_data.News;
import com.app.zilla.jinrishi.fragment.tips_fragment.html_data.NewsApiService;
import com.app.zilla.jinrishi.fragment.tips_fragment.html_data.NewsGson;
import com.app.zilla.jinrishi.utils.PixUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 74434 on 2018/3/21.
 */

public class TipsFragment extends Fragment {

    private NewsAdapter adapter;

    private int page = 0;

    private EasyRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter = new NewsAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //添加边框
        SpaceDecoration itemDecoration = new SpaceDecoration((int) PixUtil.convertDpToPixel(8, getContext()));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);

        //更多加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getData();
            }

            @Override
            public void onMoreClick() {

            }
        });
        //写刷新事件
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        page = 0;
                        getData();
                    }
                }, 1000);
            }
        });

        //点击事件
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ArrayList<String> data = new ArrayList<String>();
                data.add(adapter.getAllData().get(position).getPicUrl());
                data.add(adapter.getAllData().get(position).getUrl());
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                //用Bundle携带数据
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", data);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        Log.d("page", page + "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppInfo.BASE_URL)
                //String
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                //    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .build();
        NewsApiService apiManager = retrofit.create(NewsApiService.class);//这里采用的是Java的动态代理模式
        apiManager.getNewsData(AppInfo.API_KEY, "10", page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<NewsGson, List<News>>() {
                    @Override
                    public List<News> call(NewsGson newsgson) { //
                        List<News> newsList = new ArrayList<News>();
                        for (NewsGson.NewslistBean newslistBean : newsgson.getNewslist()) {
                            News new1 = new News();
                            new1.setTitle(newslistBean.getTitle());
                            new1.setCtime(newslistBean.getCtime());
                            new1.setDescription(newslistBean.getDescription());
                            new1.setPicUrl(newslistBean.getPicUrl());
                            new1.setUrl(newslistBean.getUrl());
                            newsList.add(new1);
                        }
                        return newsList; // 返回类型
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onNext(List<News> newsList) {
                        adapter.addAll(newsList);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),
                                "网络连接失败", Toast.LENGTH_LONG).show();
                    }
                });
        page = page + 1;
    }
}

/*package com.app.zilla.jinrishi.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.fragment.tips_fragment.OneFm1;
import com.app.zilla.jinrishi.fragment.tips_fragment.TipsFmAdapter;
import com.app.zilla.jinrishi.fragment.tips_fragment.UpTabInfo;
import com.app.zilla.jinrishi.fragment.tips_fragment.UpTabs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

/*
public class TipsFragment extends Fragment implements ViewPager.OnPageChangeListener {

        private View view;
        private RadioGroup rg_;
        private ViewPager vp_;
        private HorizontalScrollView hv_;
        private List<Fragment> newsList = new ArrayList<Fragment>();
        private TipsFmAdapter adapter;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (view == null) {
                //初始化view
                view = inflater.inflate(R.layout.fragment_tips, container,false);
                rg_ = (RadioGroup) view.findViewById(R.id.one_rg);
                vp_ = (ViewPager) view.findViewById(R.id.one_view);
                hv_ = (HorizontalScrollView) view.findViewById(R.id.one_hv);
                //设置RadioGroup点击事件
                rg_.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int id) {
                        vp_.setCurrentItem(id);
                    }
                });
                //初始化顶部导航栏
                initTab(inflater);
                //初始化viewpager
                initView();
            }
        /*
         * 底部导航栏切换后 由于没有销毁顶部设置导致如果没有重新设置view
         * 导致底部切换后切回顶部页面数据会消失等bug
         * 以下设置每次重新创建view即可
        */
/*
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        /***
         * 初始化viewpager
         */
/*
        private void initView() {
            List<UpTabInfo> tabs = UpTabs.getSelected();
            for (int i = 0; i < tabs.size(); i++) {
                OneFm1 fm1 = new OneFm1();
                Bundle bundle = new Bundle();
                bundle.putString("name", tabs.get(i).getName());
                fm1.setArguments(bundle);
                newsList.add(fm1);
            }
            //设置viewpager适配器
            adapter = new TipsFmAdapter(getActivity().getSupportFragmentManager(),newsList);
            vp_.setAdapter(adapter);
            //两个viewpager切换不重新加载
            vp_.setOffscreenPageLimit(2);
            //设置默认
            vp_.setCurrentItem(0);
            //设置viewpager监听事件
            vp_.setOnPageChangeListener(this);
        }
        /***
         * 初始化头部导航栏
         * @param inflater
         */
/*
        private void initTab(LayoutInflater inflater) {
            List<UpTabInfo> hTabs = UpTabs.getSelected();
            for (int i = 0; i < hTabs.size(); i++) {
                //设置头部项布局初始化数据
                RadioButton rbButton  = (RadioButton) inflater.inflate(R.layout.up_tab_bar, null);
                rbButton.setId(i);
                rbButton.setText(hTabs.get(i).getName());
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT);
                //加入RadioGroup
                rg_.addView(rbButton,params);
            }
            //默认点击
            rg_.check(0);
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
        @Override
        public void onPageSelected(int id) {
            setTab(id);
        }
        /***
         * 页面跳转切换头部偏移设置
         * @param id
         */
/*
        private void setTab(int id) {
            RadioButton rbButton = (RadioButton) rg_.getChildAt(id);
            //设置标题被点击
            rbButton.setChecked(true);
            //偏移设置
            int left = rbButton.getLeft();
            int width = rbButton.getMeasuredWidth();
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int screenWidth = metrics.widthPixels;
            //移动距离= 左边的位置 + button宽度的一半 - 屏幕宽度的一半
            int len = left + width / 2 - screenWidth / 2;
            //移动
            hv_.smoothScrollTo(len, 0);
        }
    }

    */

