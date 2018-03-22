package com.app.zilla.jinrishi.fragment.tips_fragment.Easyrecycle;

import android.content.Context;
import android.view.ViewGroup;

import com.app.zilla.jinrishi.fragment.tips_fragment.html_data.News;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class NewsAdapter extends RecyclerArrayAdapter<News> {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        return new NewsViewHolder(parent);
    }
}
