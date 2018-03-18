package com.app.zilla.jinrishi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.zilla.jinrishi.BmobObj.Campus;
import com.app.zilla.jinrishi.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by 74434 on 2018/3/17.
 */

public class CampusItemAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private List<Campus> mList;
    //布局加载器
    private LayoutInflater inflater;
    private Campus data;

    private Callback mCallback;

    /**

     * 自定义接口，用于回调按钮点击事件到Activity

     */

    public interface Callback {
        void click(View v);
    }

    public CampusItemAdapter(Context mContext, List<Campus> mList, Callback callback) {
        this.mContext = mContext;
        this.mList = mList;
        mCallback = callback;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder1=null;
        //如果是第一次加载
        if(view==null){
            viewHolder1=new ViewHolder();
            view=inflater.inflate(R.layout.simple_item_layout,null);

            viewHolder1.campus_name=(TextView)view.findViewById(R.id.one_item);

            //设置缓存
            view.setTag(viewHolder1);
        }
        else {
            viewHolder1 = (ViewHolder)view.getTag();
        }

        //设置数据
        data=mList.get(i);

        viewHolder1.campus_name.setText(data.getCampus_name());

        return view;
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View view) {
        mCallback.click(view);
    }


    class ViewHolder{
        private TextView campus_name;
    }
}
