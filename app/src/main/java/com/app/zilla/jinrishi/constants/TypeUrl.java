package com.app.zilla.jinrishi.constants;

/**
 * Created by 74434 on 2018/3/21.
 */

public class TypeUrl {

    public static String getTypeUrl(int type){
        switch (type){
            case 0:
                return "social";
            case 1:
                return "guonei";
            case 2:
                return "world";
            default:
                return "social";
        }
    }
}
