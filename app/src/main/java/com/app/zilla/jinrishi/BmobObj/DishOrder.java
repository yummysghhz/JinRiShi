package com.app.zilla.jinrishi.BmobObj;

import org.w3c.dom.ProcessingInstruction;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/3/9.
 */

public class DishOrder extends BmobObject {
    private JrsUser userInfo;
    private Dish dishInfo;
    private boolean likeOrN;

    public JrsUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(JrsUser userInfo) {
        this.userInfo = userInfo;
    }

    public Dish getDishInfo() {
        return dishInfo;
    }

    public void setDishInfo(Dish dishInfo) {
        this.dishInfo = dishInfo;
    }

    public boolean isLikeOrN() {
        return likeOrN;
    }

    public void setLikeOrN(boolean likeOrN) {
        this.likeOrN = likeOrN;
    }
}
