package com.example.frostmaki.mvpdemo.model;


import com.example.frostmaki.mvpdemo.bean.User;

public class UserModelImpl implements IUserModel{
    @Override
    public String login(User user) {
        boolean networkError = false; //网络是否异常

        try {
            Thread.sleep(3000);//模拟网络连接
            if (networkError) {
                return "网络异常";
            } else if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
                return "true";
            } else {
                return "账号或密码错误";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
