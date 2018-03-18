package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/3/9.
 */

public class Canteen extends BmobObject {
    private String canteen_name;
    private Campus campusIn;

    public String getCanteen_name() {
        return canteen_name;
    }

    public void setCanteen_name(String canteen_name) {
        this.canteen_name = canteen_name;
    }

    public Campus getCampusIn() {
        return campusIn;
    }

    public void setCampusIn(Campus campusIn) {
        this.campusIn = campusIn;
    }

    @Override
    public String toString() {
        return canteen_name+" in "
                +campusIn.toString();
    }
}
