package com.umeng.soexample.data.jiuview;

import com.umeng.soexample.bean.JiuBean;

import java.util.List;

public interface JiuView {

    void getCategory(List<JiuBean.DataBean> list);

    void failed(Exception e);

}
