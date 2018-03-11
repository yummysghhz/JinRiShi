package com.app.zilla.jinrishi.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Campus;
import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.DB_controller.CampusDAO;
import com.app.zilla.jinrishi.DB_controller.UserManageDAO;
import com.app.zilla.jinrishi.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
     public static RegisterActivity mactivity;
     public static Context mContext;
     public final static int REQUEST_READ_PHONE_STATE = 1;

     private EditText username_et;
     private Spinner school_sp;
     private List<String> spinner_list;
     private EditText password_et;
     private EditText password_re;
     private Button signup_btn;
     private String school;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivity = this;
        mContext = this.getBaseContext();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//去掉信息栏

        setContentView(R.layout.activity_register);

        Bmob.initialize(this, "27d282ae137c850d3661070385de75e3");

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {

            username_et = (EditText) findViewById(R.id.username_et);
            school_sp = (Spinner) findViewById(R.id.school_sp);
            password_et = (EditText) findViewById(R.id.password_et);
            password_re = (EditText) findViewById(R.id.password_re);

            signup_btn = (Button) findViewById(R.id.signup_btn);

            //new CampusDAO().getAllCampus();

            BmobQuery<Campus> query = new BmobQuery<Campus>();
            //执行查询方法
            query.findObjects(new FindListener<Campus>() {
                @Override
                public void done(final List<Campus> list, BmobException e) {
                    if(e==null){
                        for (Campus campus : list) {
                            campus.getCampus_name();
                            campus.getCampus_address();
                            spinner_list.add(campus.getCampus_name());
                        }

                    }else{
                        Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });


            //设置ArrayAdapter内置的item样式-这里是单行显示样式
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_list);
            //这里设置的是Spinner的样式
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            //设置Adapter
            school_sp.setAdapter(adapter);
            //监听Spinner的操作
            school_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                //选取时候的操作
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(RegisterActivity.this, spinner_list.get((int) id), Toast.LENGTH_SHORT);
                    school = school_sp.getItemAtPosition((int) id).toString();
                }

                //没被选取时的操作
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Toast.makeText(RegisterActivity.this, "请选择学校~", Toast.LENGTH_SHORT);
                }
            });
        }

        signup_btn.setOnClickListener(new View.OnClickListener() {
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

        //profile_img path todo
        if(TextUtils.isEmpty(username)||
                TextUtils.isEmpty(password)||
                TextUtils.isEmpty(password1)||
                TextUtils.isEmpty(school)){
            Toast.makeText(RegisterActivity.this,"用户名、密码、学校不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password1.equals(password)){
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        new UserManageDAO().registerUser(username,password,school);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }
}
