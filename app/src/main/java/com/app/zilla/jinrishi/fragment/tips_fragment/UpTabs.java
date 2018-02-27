package com.app.zilla.jinrishi.fragment.tips_fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 74434 on 2018/2/22.
 */

public class UpTabs {
    private static final List<UpTabInfo> Selected = new ArrayList<UpTabInfo>();
    static{
        Selected.add(new UpTabInfo("今日"));
        Selected.add(new UpTabInfo("头条"));
        Selected.add(new UpTabInfo("娱乐"));
        Selected.add(new UpTabInfo("财经"));
        Selected.add(new UpTabInfo("军事"));
        Selected.add(new UpTabInfo("科技"));
        Selected.add(new UpTabInfo("时尚"));
        Selected.add(new UpTabInfo("体育"));
    }
    /***
     * 获得头部tab的所有项
     */
    public static List<UpTabInfo> getSelected() {
        return Selected;
    }
}
