package com.app.zilla.jinrishi.DB_controller;

import android.os.Handler;
import android.os.Message;
import android.text.style.MaskFilterSpan;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.zilla.jinrishi.BmobObj.Campus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 74434 on 2018/3/9.
 */

public class CampusDAO {
    public void getAllCampus(){
        BmobQuery<Campus> query = new BmobQuery<Campus>();
        //执行查询方法
        query.findObjects(new FindListener<Campus>() {
            @Override
            public void done(final List<Campus> list, BmobException e) {
                if(e==null){
                    for (Campus campus : list) {
                        campus.getCampus_name();
                        campus.getCampus_address();
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }

        });
    }
}
