package com.app.zilla.jinrishi.fragment.tips_fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 74434 on 2018/2/22.
 */

public class UpTabs {
    private static final List<UpTabInfo> Selected = new ArrayList<UpTabInfo>();
    static{
        Selected.add(new UpTabInfo(" 美食 "));
        Selected.add(new UpTabInfo(" 健康 "));
        Selected.add(new UpTabInfo(" 精选 "));
    }
    /***
     * 获得头部tab的所有项
     */
    public static List<UpTabInfo> getSelected() {
        return Selected;
    }
}
