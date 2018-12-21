package com.umeng.soexample.data.miaoprodusctpresenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.umeng.soexample.bean.MiaoProduct;
import com.umeng.soexample.data.miaoprodusctmodel.MiaoProductModel;
import com.umeng.soexample.data.miaoprodusctview.MiaoProductView;

public class MiaoProductPresenter {

    private MiaoProductView miaoProductView;

    public MiaoProductPresenter(MiaoProductView miaoProductView) {
        this.miaoProductView = miaoProductView;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MiaoProduct miaoProduct = (MiaoProduct) msg.obj;
            miaoProductView.getProduct(miaoProduct.getData());
        }
    };


    public void getMiaoProduct() {
        new Thread() {
            @Override
            public void run() {
                MiaoProduct miaoProductData = MiaoProductModel.getMiaoProductData();
                Message message = mHandler.obtainMessage();
                message.obj = miaoProductData;
                mHandler.sendMessage(message);
                super.run();
            }
        }.start();
    }


}
