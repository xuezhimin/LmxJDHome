package com.umeng.soexample.data.miaoprodusctview;

import com.umeng.soexample.bean.MiaoProduct;

import java.util.List;

public interface MiaoProductView {

    void getProduct(List<MiaoProduct.DataBean> list);

    void failed(Exception e);


}
