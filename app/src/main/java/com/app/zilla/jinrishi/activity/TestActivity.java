package com.app.zilla.jinrishi.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.zilla.jinrishi.BmobObj.Canteen;
import com.app.zilla.jinrishi.DB_controller.CanteenDAO;
import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.constants.AppInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class TestActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> mylist=new ArrayList<String>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mylist.clear();
            List<Canteen> list = (List<Canteen>) msg.obj;
            for (Canteen canteen : list) {
                System.out.println(canteen.toString());
                mylist.add(canteen.toString());
            };

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(TestActivity.this,R.layout.simple_item_layout,mylist);
            listView.setAdapter(adapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, AppInfo.APP_KEY);
        setContentView(R.layout.activity_test);
        listView=(ListView)findViewById(R.id.listView);
        CanteenDAO.getCampusCanteen(handler);
    }
}
