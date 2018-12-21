package com.umeng.soexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.R;
import com.umeng.soexample.login.LoginPresenter;
import com.umeng.soexample.login.LoginView;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    /**
     * 手机快速登录
     */
    private TextView mTxtReg;
    private ImageView mImgQqLogin;
    private UMAuthListener authListener;
    /**
     * 用户名/邮箱/手机号
     */
    private EditText mEditName;
    /**
     * 登录密码
     */
    private EditText mEditPwd;
    /**
     * 登录
     */
    private Button mBtnLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //第三方QQ登录的监听
        authListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //登录成功  页面跳转
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                Toast.makeText(getBaseContext(), "QQ登录成功", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        };

        //实例化p层
        presenter = new LoginPresenter(this);


    }

    private void initView() {
        mTxtReg = findViewById(R.id.txt_reg);
        mTxtReg.setOnClickListener(this);
        mImgQqLogin = findViewById(R.id.img_qq_login);
        mImgQqLogin.setOnClickListener(this);
        mEditName = findViewById(R.id.edit_name);
        mEditPwd = findViewById(R.id.edit_pwd);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //快速注册
            case R.id.txt_reg:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            //第三方QQ登录
            case R.id.img_qq_login:
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            //原生接口登录
            case R.id.btn_login:
                String name = mEditName.getText().toString();
                String pwd = mEditPwd.getText().toString();
                presenter.login(name, pwd);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult
            (int requestCode, String permissions[], int[] grantResults) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    //登录接口实现的方法
    @Override
    public void onSuccess(String result) {
        Toast.makeText(getBaseContext(), result + "", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getBaseContext(), msg + "", Toast.LENGTH_SHORT).show();
    }
}
