package com.umeng.soexample.data.listpresenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.umeng.soexample.bean.Root;
import com.umeng.soexample.data.listmodel.ListDataModel;
import com.umeng.soexample.data.listview.ListDataView;

public class ListDataPresenter {

    private ListDataView listDataView;

    public ListDataPresenter(ListDataView listDataView) {
        this.listDataView = listDataView;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Root root = (Root) msg.obj;
            listDataView.onSucess(root.getData());
        }
    };


    public void getData() {
        new Thread() {
            @Override
            public void run() {
                Root listData = ListDataModel.getListData();
                Message mMessage = mHandler.obtainMessage();
                mMessage.obj = listData;
                mHandler.sendMessage(mMessage);
            }
        }.start();
    }


}
