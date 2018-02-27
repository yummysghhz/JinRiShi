package com.app.zilla.jinrishi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
     private ImageButton profile_img_btn;
     private EditText username_et;
     private Spinner school_sp;
     private EditText password_et;
     private EditText password_re;
     private EditText phone_et;
     private EditText identifier_et;
     private TextView send_txt;
     private Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//去掉信息栏

        setContentView(R.layout.activity_register);

        Bmob.initialize(this, "27d282ae137c850d3661070385de75e3");

        profile_img_btn=(ImageButton) findViewById(R.id.profile_img_btn);
        username_et=(EditText) findViewById(R.id.username_et);
        school_sp=(Spinner) findViewById(R.id.school_sp);
        password_et=(EditText) findViewById(R.id.password_et);
        password_re=(EditText) findViewById(R.id.password_re);

        signup_btn=(Button) findViewById(R.id.signup_btn);

        profile_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });



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
                TextUtils.isEmpty(password1)){
            Toast.makeText(RegisterActivity.this,"用户名、密码不可为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password1.equals(password)){
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }

        final JrsUser user = new JrsUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<JrsUser>() {
            @Override
            public void done(JrsUser jrsUser, BmobException e) {
                if(e==null){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
