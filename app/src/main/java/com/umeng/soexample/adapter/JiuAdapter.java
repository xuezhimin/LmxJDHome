package com.umeng.soexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.JiuBean;

import java.util.List;

public class JiuAdapter extends BaseAdapter {
    private Context context;
    private List<JiuBean.DataBean> list;

    public JiuAdapter(Context context, List<JiuBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_home_item_jiu, null);
            viewHolder.imageView = convertView.findViewById(R.id.home_item_jiu_img);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(list.get(position).getIcon()).into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
    }
}
