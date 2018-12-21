package com.umeng.soexample.data.jiupresenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.umeng.soexample.bean.JiuBean;
import com.umeng.soexample.data.jiumodel.JiuModel;
import com.umeng.soexample.data.jiuview.JiuView;

public class JiuPresenter {

    private JiuView jiuView;

    public JiuPresenter(JiuView jiuView) {
        this.jiuView = jiuView;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JiuBean jiuBean = (JiuBean) msg.obj;
            jiuView.getCategory(jiuBean.getData());
        }
    };


    public void getJiuData() {
        new Thread() {
            @Override
            public void run() {
                JiuBean jiuData = JiuModel.getJiuData();
                Message message = mHandler.obtainMessage();
                message.obj = jiuData;
                mHandler.sendMessage(message);
                super.run();
            }
        }.start();
    }

}
