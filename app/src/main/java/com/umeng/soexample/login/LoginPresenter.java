package com.umeng.soexample.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.umeng.soexample.bean.LoginReg;

public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
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
                    loginView.onSuccess(msg1);
                    break;
                case "1":
                    loginView.onFailed(msg1);
                    break;
            }
        }
    };


    public void login(final String name, final String pwd) {
        new Thread() {
            @Override
            public void run() {
                LoginReg login = LoginModel.login(name, pwd);
                Message message = mHandler.obtainMessage();
                message.obj = login;
                mHandler.sendMessage(message);
                super.run();

            }
        }.start();
    }


}
