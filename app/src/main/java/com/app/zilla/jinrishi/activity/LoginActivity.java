package com.app.zilla.jinrishi.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.utils.AppInfo;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity {
    public static Context mContext;
    public static Activity mActivity;
    private Button login_btn;
    private TextView qqlogin_txt;
    private TextView register_txt;
    private EditText name_et;
    private EditText password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//去掉信息栏
        mActivity = this;
        mContext = this.getBaseContext();
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, AppInfo.getAppkey());

        login_btn=(Button) findViewById(R.id.login_btn);
        login_btn=(Button) findViewById(R.id.login_btn);
        qqlogin_txt=(TextView) findViewById(R.id.loginQQ);
        register_txt=(TextView) findViewById(R.id.register);
        name_et=(EditText) findViewById(R.id.username);
        password_et=(EditText)findViewById(R.id.password);

        register_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //登录
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        //第三方登录
        qqlogin_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void loginUser() {
        String username=name_et.getText().toString();
        String password=password_et.getText().toString();

        if(TextUtils.isEmpty(username)||
                TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"用户名、密码不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        JrsUser.loginByAccount(username, password, new LogInListener<JrsUser>() {
            @Override
            public void done(JrsUser jrsUser, BmobException e) {
                if (jrsUser!=null){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.mContext,TestActivity.class);
                    LoginActivity.mContext.startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
