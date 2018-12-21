package com.umeng.soexample.register;

import com.google.gson.Gson;
import com.umeng.soexample.bean.LoginReg;
import com.umeng.soexample.httputils.Utils;

public class RegModel {

    public static LoginReg reg(String name, String pwd) {
        String regUrl = Utils.get("http://www.zhaoapi.cn/user/reg?mobile="
                + name + "&password=" + pwd);
        Gson gson = new Gson();
        LoginReg loginReg = gson.fromJson(regUrl, LoginReg.class);
        return loginReg;
    }

}
