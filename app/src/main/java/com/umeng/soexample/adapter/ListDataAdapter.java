package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.Data;

import java.util.List;
import java.util.Random;

public class ListDataAdapter extends RecyclerView.Adapter<ListDataAdapter.ViewHolder> {

    private Context mContext;
    private List<Data> mListData;

    public ListDataAdapter(Context mContext, List<Data> mListData) {
        this.mContext = mContext;
        this.mListData = mListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.fragment_home_item_list_data, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //图片拆分----------------------------------------------------------------------
        String images = mListData.get(position).getImages();
        String[] split = images.split("\\|");
        if (split.length > 0) {
            Glide.with(mContext).load(split[0]).into(holder.imageView);
        }

        //瀑布流的关键之处
        Random random = new Random();
        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
        layoutParams.height = random.nextInt(200) + 50;
        holder.textView.setLayoutParams(layoutParams);

        holder.textView.setText(mListData.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.home_list_data_img);
            textView = itemView.findViewById(R.id.home_list_data_title);
        }
    }

}
