package com.umeng.soexample.register;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.umeng.soexample.bean.LoginReg;

public class RegPresenter {

    private RegView regView;

    public RegPresenter(RegView regView) {
        this.regView = regView;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoginReg loginReg = (LoginReg) msg.obj;
            String code = loginReg.getCode();
            String msg1 = loginReg.getMsg();
            switch (code) {
                case "0":
                    regView.onSuccess(msg1);
                    break;
                case "1":
                    regView.onFailed(msg1);
                    break;
            }
        }
    };


    public void reg(final String name, final String pwd) {
        new Thread() {
            @Override
            public void run() {
                LoginReg reg = RegModel.reg(name, pwd);
                Message message = mHandler.obtainMessage();
                message.obj = reg;
                mHandler.sendMessage(message);
                super.run();

            }
        }.start();
    }

}
