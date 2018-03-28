package com.app.zilla.jinrishi.DB_controller;

import com.app.zilla.jinrishi.BmobObj.DishOrder;
import com.app.zilla.jinrishi.BmobObj.JrsUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class DishOrderDAO {
    //主要写增 查，删改不写
    //查找：按User查

    public static void getMyDishOrder() {
        JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);
        BmobQuery<DishOrder> query=new BmobQuery<>();
        query.addWhereEqualTo("userInfo",currentUser);
        //排序？？？
        query.findObjects(new FindListener<DishOrder>() {
            @Override
            public void done(List<DishOrder> list, BmobException e) {
                if(e==null){
                    list.toString();
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    public static void addDishOrder() {
        JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);
        BmobQuery<DishOrder> query=new BmobQuery<>();

    }
}
