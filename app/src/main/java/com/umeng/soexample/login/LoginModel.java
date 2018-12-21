package com.umeng.soexample.login;

import com.google.gson.Gson;
import com.umeng.soexample.bean.LoginReg;
import com.umeng.soexample.httputils.Utils;

public class LoginModel {

    public static LoginReg login(String name, String pwd) {
        String loginUrl = Utils.get("http://www.zhaoapi.cn/user/login?mobile="
                + name + "&password=" + pwd);
        Gson gson = new Gson();
        LoginReg loginReg = gson.fromJson(loginUrl, LoginReg.class);
        return loginReg;
    }

}
