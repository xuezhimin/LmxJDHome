package com.umeng.soexample.data.listmodel;

import com.google.gson.Gson;
import com.umeng.soexample.bean.Root;
import com.umeng.soexample.httputils.Utils;

public class ListDataModel {

    public static Root getListData() {
        String listShowData = Utils.get("http://www.zhaoapi.cn/product/getProducts?pscid=2");
        Gson gson = new Gson();
        Root root = gson.fromJson(listShowData, Root.class);
        return root;
    }


}
