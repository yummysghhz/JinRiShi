package com.app.zilla.jinrishi.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.fragment.MyFragment;
import com.app.zilla.jinrishi.fragment.RecFragment;
import com.app.zilla.jinrishi.fragment.TipsFragment;
import com.app.zilla.jinrishi.fragment.DeliveryFragment;
import com.hjm.bottomtabbar.BottomTabBar;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends FragmentActivity {

        private BottomTabBar mBottomBar;
        private SystemBarTintManager tintManager;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initWindow();

            setContentView(R.layout.activity_main);
            mBottomBar = (BottomTabBar) findViewById(R.id.bottom_bar);

            mBottomBar.init(getSupportFragmentManager())
                    .setImgSize(85, 85)
                    .setFontSize(12)
                    .setTabPadding(4, 6, 10)
                    .setChangeColor(Color.parseColor("#F8F8FF"), Color.parseColor("#B2DBD5"))
                    .addTabItem("推荐", R.mipmap.canteen, RecFragment.class)
                    .addTabItem("外送", R.mipmap.delivery, DeliveryFragment.class)
                    .addTabItem("资讯", R.mipmap.tips, TipsFragment.class)
                    .addTabItem("我的", R.mipmap.myinfo, MyFragment.class)
                    .setTabBarBackgroundColor(Color.parseColor("#209E85"))
                    .isShowDivider(false)
                    .setCurrentTab(1)
                    .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                        @Override
                        public void onTabChange(int position, String name, View view) {
                            Log.i("TGA", "位置：" + position + "      选项卡：" + name);

                            //if (position == 2) {
                            //    showTree(view);
                            // }
                        }
                    });
        }

    @TargetApi(Build.VERSION_CODES.M)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//去掉信息栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(Color.TRANSPARENT);
            tintManager.setStatusBarTintEnabled(true);
        }
    }


    private void showTree(View view) {
            // 获取自定义布局文件activity_popupwindow_left.xml的视图
            View popupWindow_view = getLayoutInflater().inflate(R.layout.test_pop, null, false);
            // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
            PopupWindow window = new PopupWindow(popupWindow_view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
            window.setFocusable(true);
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            window.setBackgroundDrawable(dw);
            // 在底部显示
            window.showAtLocation(view, Gravity.BOTTOM, 0, 0);


            popupWindow_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBottomBar.setCurrentTab(0);
                }
            });
        }


}
