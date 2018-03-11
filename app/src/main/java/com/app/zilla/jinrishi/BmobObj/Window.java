package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/3/9.
 */

public class Window extends BmobObject {
    private String window_name;
    private Canteen canteenIn;

    public String getWindow_name() {
        return window_name;
    }

    public void setWindow_name(String window_name) {
        this.window_name = window_name;
    }

    public Canteen getCanteenIn() {
        return canteenIn;
    }

    public void setCanteenIn(Canteen canteenIn) {
        this.canteenIn = canteenIn;
    }
}
