package com.umeng.soexample.data.jiumodel;

import com.google.gson.Gson;
import com.umeng.soexample.bean.JiuBean;
import com.umeng.soexample.httputils.Utils;

public class JiuModel {

    public static JiuBean getJiuData() {
        String jiuDataUrl = Utils.get("http://www.zhaoapi.cn/product/getCatagory");
        Gson gson = new Gson();
        JiuBean jiuBean = gson.fromJson(jiuDataUrl, JiuBean.class);
        return jiuBean;
    }

}
