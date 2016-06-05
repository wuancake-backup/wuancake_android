package com.example.frostmaki.mvpdemo;


import android.app.Activity;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frostmaki.mvpdemo.bean.User;
import com.example.frostmaki.mvpdemo.presenter.UserPresenter;
import com.example.frostmaki.mvpdemo.view.IUserView;


public class UserActivity extends Activity implements IUserView, View.OnClickListener {
    private UserPresenter userPresenter;
    private EditText etUserName;
    private EditText etPassword;
    private Button btnlogin,btclear;
    private ProgressDialog loginProgreess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.account);
        etPassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        btclear=(Button)findViewById(R.id.clear);
        //loginProgreess=new ProgressDialog(UserActivity.this);
        btnlogin.setOnClickListener(this);
        userPresenter = new UserPresenter(this);

    }

    @Override
    public void loginSuccess() {
        loginProgreess.dismiss();
        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String error) {
        loginProgreess.dismiss();
        Toast.makeText(getApplicationContext(), "登录失败:"+error , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login: {
                String username = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginProgreess = ProgressDialog.show(this, "登录", "正在登录...");
                userPresenter.login_p(new User(username, password));
            }
            break;
            case R.id.clear: {
                etUserName.setText("");
                etPassword.setText("");
            }
            break;
        }

    }
}
