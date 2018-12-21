package com.umeng.soexample.data.miaoprodusctmodel;

import com.google.gson.Gson;
import com.umeng.soexample.bean.MiaoProduct;
import com.umeng.soexample.httputils.Utils;

public class MiaoProductModel {

    public static MiaoProduct getMiaoProductData() {
        String jiuDataUrl = Utils.get("http://www.wanandroid.com/tools/mockapi/6523/restaurants_offset_0_limit_4");
        Gson gson = new Gson();
        MiaoProduct miaoProduct = gson.fromJson(jiuDataUrl, MiaoProduct.class);
        return miaoProduct;
    }

}
