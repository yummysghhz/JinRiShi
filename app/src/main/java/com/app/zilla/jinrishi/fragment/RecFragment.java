package com.app.zilla.jinrishi.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Canteen;
import com.app.zilla.jinrishi.BmobObj.Window;
import com.app.zilla.jinrishi.DB_controller.CanteenDAO;
import com.app.zilla.jinrishi.DB_controller.WindowDAO;
import com.app.zilla.jinrishi.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RecFragment extends Fragment {
    private View view;
    private ExpandableListView expandableListView;
    //最外面一层 分组名
    List<Canteen> canteenList;
    //最外面一层 分组下面的详情
    List<List<Window>> windowList;
    //自定义的适配器
    private ExpandableAdapter expandableAdapter;

    //是否使用默认的指示器 默认true 使用者可以在这里通过改变这个值观察默认指示器和自定义指示器的区别
    private boolean use_default_indicator = false;

    private MyHandler handler= new MyHandler(this);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            //初始化view
            view = inflater.inflate(R.layout.fragment_rec, container, false);
            expandableListView=view.findViewById(R.id.canteen_window_list);
            if ( use_default_indicator ) {
                //不做处理就是默认
            } else {
                expandableListView.setGroupIndicator(null);
            }

            canteenList=new ArrayList<>();
            windowList=new ArrayList<>();

            expandableAdapter=new ExpandableAdapter(this.getActivity(),canteenList,R.layout.item_expand_canteen,windowList,R.layout.item_expand_window);
            expandableListView.setAdapter(expandableAdapter);

            Toast.makeText(this.getActivity(),"数据加载中",Toast.LENGTH_SHORT).show();
            CanteenDAO.getCampusCanteen(handler);

            //取不到
            canteenList.toString();

            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                    if(expandableListView.isGroupExpanded(groupPosition)){
                        expandableListView.collapseGroup(groupPosition);
                    }
                    else{
                        Toast.makeText(getActivity(),"数据加载中",Toast.LENGTH_SHORT).show();
                        //把canteen传过去
                        WindowDAO.getCanteenWindow(handler,canteenList.get(groupPosition),groupPosition);
                    }
                    return true;
                }
            });

            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                    List<Window> canteenWindows=windowList.get(groupPosition);
                    if(canteenWindows==null){
                        Window window=canteenWindows.get(childPosition);
                        if(window!=null){
                            String winName=window.getWindow_name();
                            if(!TextUtils.isEmpty(winName)){
                                Toast.makeText(getContext(),winName,Toast.LENGTH_SHORT).show();
                                //todo Intent--->RecDishActivity


                            }
                        }
                    }
                    return false;
                }
            });
        }
        return view;
    }


    private class MyHandler extends Handler{
        WeakReference<RecFragment> recFragmentWeakReference;
        MyHandler(RecFragment recFragment){
            recFragmentWeakReference=new WeakReference(recFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                //往对应分组的详情里面添加数据
                List<Window> windows=windowList.get(msg.arg1);
                windows=(List<Window>)msg.obj;


                //windowList.
                expandableAdapter.notifyDataSetChanged();
            }else{
                //第一次加载数据的时候 添加分组信息
                canteenList=(List<Canteen>) msg.obj;
                for(Canteen canteen:canteenList){
                    List<Window> tempWindows=new ArrayList<>();
                    windowList.add(tempWindows);
                }
                expandableAdapter.notifyDataSetChanged();
            }
        }
    }


    class ExpandableAdapter extends BaseExpandableListAdapter {
    //视图加载器
    private LayoutInflater mInflater;
    private Context mContext;
    private int mExpandedGroupLayout;
    private int mChildLayout;
    private List<Canteen> mGroupArray;
    private List<List<Window>> mChildArray;

    public ExpandableAdapter(Context context, List<Canteen> groupData, int expandedGroupLayout,
                             List<List<Window>> childData, int childLayout) {
        mContext = context;
        mExpandedGroupLayout = expandedGroupLayout;
        mChildLayout = childLayout;
        mGroupArray = groupData;
        mChildArray = childData;
        mInflater = ( LayoutInflater ) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return windowList.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // 取得显示给定分组给定子位置的数据用的视图。
        View v;
        if ( convertView == null ) {
            v = newChildView(parent);
        } else {
            v = convertView;
        }
        bindChildView(v, mChildArray.get(groupPosition).get(childPosition));
        return v;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 取得指定分组的子元素数。
        return mChildArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // 取得与给定分组关联的数据。
        return mGroupArray.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // 取得分组数
        return mGroupArray.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // 取得指定分组的ID。该组ID必须在组中是唯一的。组合的ID （参见getCombinedGroupId(long)）
        // 必须不同于其他所有ID（分组及子项目的ID）。
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // 取得用于显示给定分组的视图。 这个方法仅返回分组的视图对象， 要想获取子元素的视图对象，
        // 就需要调用 getChildView(int, int, boolean, View, ViewGroup)。
        View v;
        if ( convertView == null ) {
            v = newGroupView(parent);
        } else {
            v = convertView;
        }
        bindGroupView(v, mGroupArray.get(groupPosition), isExpanded);
        return v;
    }

    /**
     * 绑定组数据
     *
     * @param view
     * @param data
     * @param isExpanded
     */
    private void bindGroupView(View view, Canteen data, boolean isExpanded) {
        // 绑定组视图的数据 当然这些都是模拟的
        TextView canteen_tv=view.findViewById(R.id.label_group_canteen);
        canteen_tv.setText(data.getCanteen_name());
        if ( !use_default_indicator ) {
            ImageView iv_tip = (ImageView) view.findViewById(R.id.iv_tip);
            if ( isExpanded ) {
                iv_tip.setImageResource(R.drawable.down);
            } else {
                iv_tip.setImageResource(R.drawable.right);
            }
        }
    }

    /**
     * 绑定子数据
     *
     * @param view
     * @param data
     */
    private void bindChildView(View view, Window data) {
        // 绑定组视图的数据 当然这些都是模拟的
        TextView window_tv=view.findViewById(R.id.label_expand_window);
        window_tv.setText(data.getWindow_name());
    }

    /**
     * 创建新的组视图
     *
     * @param parent
     * @return
     */
    public View newGroupView(ViewGroup parent) {
        return mInflater.inflate(mExpandedGroupLayout, parent, false);
    }

    /**
     * 创建新的子视图
     *
     * @param parent
     * @return
     */
    public View newChildView(ViewGroup parent) {
        return mInflater.inflate(mChildLayout, parent, false);
    }

    public boolean hasStableIds() {
        // 是否指定分组视图及其子视图的ID对应的后台数据改变也会保持该ID。
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // 指定位置的子视图是否可选择。
        return true;
    }
}
}
