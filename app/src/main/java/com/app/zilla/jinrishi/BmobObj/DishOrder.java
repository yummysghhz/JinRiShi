package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;


public class DishOrder extends BmobObject {
    private JrsUser userInfo;
    private Dish dishInfo;
    private boolean likeOrN;

    public String getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(String meal_id) {
        this.meal_id = meal_id;
    }

    private String meal_id;

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