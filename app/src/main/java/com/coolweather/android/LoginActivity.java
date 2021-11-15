package com.coolweather.android;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.coolweather.android.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText name;
    private EditText pass;
    private boolean flag = true;
    private  Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //用户名和密码
        name = (EditText) findViewById(R.id.login_name);
        pass = (EditText) findViewById(R.id.login_password);


        //登陆验证
        login = (Button) findViewById(R.id.login_1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = DataSupport.select("name","password").find(User.class);
                if (!(TextUtils.isEmpty(name.getText().toString()))&&
                        !(TextUtils.isEmpty(pass.getText().toString()))){
                    for (User user:users){
                        if (name.getText().toString().equals(user.getName())
                                && pass.getText().toString().equals(user.getPassword())){
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            flag = false;
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("username",name.getText().toString());
                            startActivity(intent);
                        }
                    }
                    if (flag){
                        Toast.makeText(LoginActivity.this, "账户或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //注册跳转
        Button main_register = (Button) findViewById(R.id.main_register_button);
        main_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
