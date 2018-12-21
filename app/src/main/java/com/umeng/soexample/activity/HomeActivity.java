package com.umeng.soexample.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.fragment.CartFragment;
import com.umeng.soexample.fragment.FindFragment;
import com.umeng.soexample.fragment.HomeFragment;
import com.umeng.soexample.fragment.MeFragment;
import com.umeng.soexample.fragment.SortFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 首页
     */
    private TextView mTxtHomePager;
    /**
     * 分类
     */
    private TextView mTxtSort;
    /**
     * 发现
     */
    private TextView mTxtFind;
    /**
     * 购物车
     */
    private TextView mTxtCart;
    /**
     * 我的
     */
    private TextView mTxtMe;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        //添加一个默认的fragment
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.home_fragment, new HomeFragment());
        transaction.commit();


    }

    private void initView() {
        mTxtHomePager = findViewById(R.id.txt_home_pager);
        mTxtHomePager.setOnClickListener(this);
        mTxtSort = findViewById(R.id.txt_sort);
        mTxtSort.setOnClickListener(this);
        mTxtFind = findViewById(R.id.txt_find);
        mTxtFind.setOnClickListener(this);
        mTxtCart = findViewById(R.id.txt_cart);
        mTxtCart.setOnClickListener(this);
        mTxtMe = findViewById(R.id.txt_me);
        mTxtMe.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // 点击时启动trancaction事件
        transaction = manager.beginTransaction();

        switch (v.getId()) {
            case R.id.txt_home_pager:
                transaction.replace(R.id.home_fragment, new HomeFragment());
                break;
            case R.id.txt_sort:
                transaction.replace(R.id.home_fragment, new SortFragment());
                break;
            case R.id.txt_find:
                transaction.replace(R.id.home_fragment, new FindFragment());
                break;
            case R.id.txt_cart:
                transaction.replace(R.id.home_fragment, new CartFragment());
                break;
            case R.id.txt_me:
                transaction.replace(R.id.home_fragment, new MeFragment());
                break;
        }

        transaction.commit();
    }
}
