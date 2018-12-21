package com.umeng.soexample.data.listview;

import com.umeng.soexample.bean.Data;

import java.util.List;

public interface ListDataView {


    void onSucess(List<Data> entityList);

    void onFailer(Exception e);


}
