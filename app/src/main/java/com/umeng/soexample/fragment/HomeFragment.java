package com.umeng.soexample.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.JiuAdapter;
import com.umeng.soexample.adapter.ListDataAdapter;
import com.umeng.soexample.adapter.MiaoProductAdapter;
import com.umeng.soexample.bean.Data;
import com.umeng.soexample.bean.JiuBean;
import com.umeng.soexample.bean.MiaoProduct;
import com.umeng.soexample.data.jiupresenter.JiuPresenter;
import com.umeng.soexample.data.jiuview.JiuView;
import com.umeng.soexample.data.listpresenter.ListDataPresenter;
import com.umeng.soexample.data.listview.ListDataView;
import com.umeng.soexample.data.miaoprodusctpresenter.MiaoProductPresenter;
import com.umeng.soexample.data.miaoprodusctview.MiaoProductView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements JiuView, MiaoProductView, ListDataView {


    private View view;
    private ImageView mHomeImgScan;
    private Banner mHomeBanner;
    private GridView mHomeJiuGrid;
    private List<JiuBean.DataBean> mJiuList;
    private List<MiaoProduct.DataBean> mMiaoProductList;
    private List<Data> mListData;
    //放图片地址的集合
    private ArrayList<String> list_path = new ArrayList<>();
    //放标题的集合
    private ArrayList<String> list_title = new ArrayList<>();
    /**
     * 02
     */
    private TextView mHomeTxtHour;
    /**
     * 15
     */
    private TextView mHomeTxtMinute;
    /**
     * 36
     */
    private TextView mHomeTxtSecond;
    //定义初始值
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                computeTime();
                if (mHour < 10) {
                    mHomeTxtHour.setText("0" + mHour + "");
                } else {
                    mHomeTxtHour.setText("0" + mHour + "");
                }
                if (mMin < 10) {
                    mHomeTxtMinute.setText("0" + mMin + "");
                } else {
                    mHomeTxtMinute.setText(mMin + "");
                }
                if (mSecond < 10) {
                    mHomeTxtSecond.setText("0" + mSecond + "");
                } else {
                    mHomeTxtSecond.setText(mSecond + "");
                }
            }
            super.handleMessage(msg);
        }
    };
    private JiuPresenter jiuPresenter;
    private JiuAdapter jiuAdapter;
    private RecyclerView mHomeMiaoRecycle;
    private MiaoProductPresenter miaoProductPresenter;
    private MiaoProductAdapter miaoProductAdapter;
    private RecyclerView mHomeListWaterfall;
    private ListDataAdapter listDataAdapter;
    private ListDataPresenter listDataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        setBanner();
        setSecondsKill();
        jiuData();
        miaoProductData();
        showListData();


        return view;
    }


    private void initView(View view) {
        mHomeImgScan = view.findViewById(R.id.home_img_scan);
        mHomeBanner = view.findViewById(R.id.home_banner);
        mHomeTxtHour = view.findViewById(R.id.home_txt_hour);
        mHomeTxtMinute = view.findViewById(R.id.home_txt_minute);
        mHomeTxtSecond = view.findViewById(R.id.home_txt_second);
        mHomeJiuGrid = view.findViewById(R.id.home_jiu_grid);
        mHomeMiaoRecycle = view.findViewById(R.id.home_miao_recycle);
        mHomeListWaterfall = view.findViewById(R.id.home_list_waterfall);
    }


    //设置轮播图
    private void setBanner() {

        //放入轮播图图片资源
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        list_title.add("第十三界瑞丽模特大赛");
        list_title.add("文化艺术节");
        list_title.add("直播封面标准");
        list_title.add("人气谁最高，金主谁最豪气");

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        mHomeBanner.setImageLoader(new MyLoader());
        //设置图片
        mHomeBanner.setImages(list_path);
        //设置文字
        mHomeBanner.setBannerTitles(list_title);
        //设置时间
        mHomeBanner.setDelayTime(2000);
        //设置是否为自动轮播，默认是“是”。
        mHomeBanner.isAutoPlay(true);
        //必须最后调用的方法，启动轮播图。
        mHomeBanner.start();


    }


    //自定义的图片加载器轮播图
    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);
        }

        public ImageView createImageView(Context context) {
            return null;
        }

    }

    //设置JD秒杀
    private void setSecondsKill() {
        //开始倒计时
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    //倒计时计算
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }


    //九宫格的展示
    private void jiuData() {
        mJiuList = new ArrayList<>();

        //适配器
        jiuAdapter = new JiuAdapter(getContext(), mJiuList);
        mHomeJiuGrid.setAdapter(jiuAdapter);

        //实例化p层
        jiuPresenter = new JiuPresenter(this);
        jiuPresenter.getJiuData();

    }


    //秒杀商品的展示
    private void miaoProductData() {
        mMiaoProductList = new ArrayList<>();

        //适配器
        miaoProductAdapter = new MiaoProductAdapter(getContext(), mMiaoProductList);
        mHomeMiaoRecycle.setAdapter(miaoProductAdapter);

        //线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //横向
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHomeMiaoRecycle.setLayoutManager(linearLayoutManager);

        //实例化p层
        miaoProductPresenter = new MiaoProductPresenter(this);
        miaoProductPresenter.getMiaoProduct();

    }

    //显示瀑布流数据
    private void showListData() {
        mListData = new ArrayList<>();

        //适配器
        listDataAdapter = new ListDataAdapter(getContext(), mListData);
        mHomeListWaterfall.setAdapter(listDataAdapter);

        //瀑布流布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);
        mHomeListWaterfall.setLayoutManager(staggeredGridLayoutManager);

        //实例化p层
        listDataPresenter = new ListDataPresenter(this);
        listDataPresenter.getData();


    }


    //九宫格实现的接口
    @Override
    public void getCategory(List<JiuBean.DataBean> list) {
        if (list != null) {
            mJiuList.clear();
            mJiuList.addAll(list);
            jiuAdapter.notifyDataSetChanged();
        }
    }

    //秒杀商品实现的接口
    @Override
    public void getProduct(List<MiaoProduct.DataBean> list) {
        if (list != null) {
            mMiaoProductList.clear();
            mMiaoProductList.addAll(list);
            miaoProductAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getContext(), e + "", Toast.LENGTH_SHORT).show();
    }


    //底部瀑布流的数据实现的接口
    @Override
    public void onSucess(List<Data> entityList) {
        if (entityList != null) {
            mListData.clear();
            mListData.addAll(entityList);
            listDataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailer(Exception e) {

    }


}
