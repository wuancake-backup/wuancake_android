package com.example.frostmaki.mvpdemo.presenter;


import android.os.Handler;
import android.os.Looper;

import com.example.frostmaki.mvpdemo.bean.User;
import com.example.frostmaki.mvpdemo.model.IUserModel;
import com.example.frostmaki.mvpdemo.model.UserModelImpl;
import com.example.frostmaki.mvpdemo.view.IUserView;



public class UserPresenter {
    private IUserView userView;
    private IUserModel userModel;

    public UserPresenter(IUserView iUserView){
        this.userView=iUserView;
        this.userModel=new UserModelImpl();
    }

    public void login_p(final User user) {
        new Thread() {
            @Override
            public void run() {
                final String res = userModel.login(user);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if ("true".equals(res)) {
                            userView.loginSuccess();
                        } else {
                            userView.loginFail(res);
                        }
                    }
                });
            }
        }.start();
    }
}
