package com.umeng.soexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.register.RegPresenter;
import com.umeng.soexample.register.RegView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegView {

    /**
     * 请输入手机号
     */
    private EditText mEditRegName;
    /**
     * 请输入密码
     */
    private EditText mEditRegPwd;
    /**
     * 注册
     */
    private Button mBtnReg;
    private RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        //实例化p层
        presenter = new RegPresenter(this);

    }

    private void initView() {
        mEditRegName = findViewById(R.id.edit_reg_name);
        mEditRegPwd = findViewById(R.id.edit_reg_pwd);
        mBtnReg = findViewById(R.id.btn_reg);
        mBtnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg:
                String name = mEditRegName.getText().toString();
                String pwd = mEditRegPwd.getText().toString();
                presenter.reg(name, pwd);
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(getBaseContext(), result + "", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getBaseContext(), msg + "", Toast.LENGTH_SHORT).show();
    }
}
