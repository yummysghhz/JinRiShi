package com.app.zilla.jinrishi.BmobObj;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 74434 on 2018/3/9.
 */

public class Campus extends BmobObject {
    private String campus_name;
    private String campus_address;

    public String getCampus_name() {
        return campus_name;
    }

    public void setCampus_name(String campus_name) {
        this.campus_name = campus_name;
    }

    public String getCampus_address() {
        return campus_address;
    }

    public void setCampus_address(String campus_address) {
        this.campus_address = campus_address;
    }
}
