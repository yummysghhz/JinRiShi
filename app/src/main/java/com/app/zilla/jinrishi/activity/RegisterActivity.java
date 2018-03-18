package com.app.zilla.jinrishi.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Campus;
import com.app.zilla.jinrishi.DB_controller.CampusDAO;
import com.app.zilla.jinrishi.DB_controller.UserManageDAO;
import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.adapter.CampusItemAdapter;
import com.app.zilla.jinrishi.utils.AppInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RegisterActivity extends AppCompatActivity implements CampusItemAdapter.Callback{
    public static RegisterActivity mactivity;
    public static Context mContext;

    private EditText username_et;
    private Spinner school_sp;
    private List<Campus> spinner_list=new ArrayList<Campus>();
    private EditText password_et;
    private EditText password_re;
    private Button signup_btn;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                spinner_list.clear();
                List<Campus> list = (List<Campus>) msg.obj;
                for (Campus campus : list) {
                    System.out.println(campus.toString());
                    spinner_list.add(campus);
                };

                CampusItemAdapter adapter = new CampusItemAdapter(RegisterActivity.this, spinner_list,RegisterActivity.this);
                school_sp.setAdapter(adapter);


                /** 选项选择监听*/

                school_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(RegisterActivity.this, "学校:" + spinner_list.get(position).getCampus_name(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(RegisterActivity.this, "请选择学校~", Toast.LENGTH_SHORT).show();
                    }
                });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivity = this;
        mContext = this.getBaseContext();

        Bmob.initialize(this, AppInfo.getAppkey());

        initView();




    }

    public void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//去掉信息栏

        setContentView(R.layout.activity_register);
        username_et = (EditText) findViewById(R.id.username_et);
        school_sp = (Spinner) findViewById(R.id.school_sp);
        password_et = (EditText) findViewById(R.id.password_et);
        password_re = (EditText) findViewById(R.id.password_re);
        signup_btn = (Button) findViewById(R.id.signup_btn);

        CampusDAO.getAllCampus(handler);

        signup_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    public void registerUser(){
        String username=username_et.getText().toString();
        String password=password_et.getText().toString();
        String password1=password_re.getText().toString();

        Campus myCampus=(Campus)school_sp.getSelectedItem();

        if(TextUtils.isEmpty(username)||
                TextUtils.isEmpty(password)||
                TextUtils.isEmpty(password1)||
                myCampus==null){
            Toast.makeText(RegisterActivity.this,"用户名、密码、学校不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password1.equals(password)){
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        new UserManageDAO().registerUser(username,password,myCampus);
    }

    @Override
    public void click(View v) {





    }
}
