package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class StrategyRcAdapter extends RecyclerView.Adapter<StrategyRcAdapter.ViewHolder> {

    private TreeSet<Strategy> mSet;
    private Context mContext;

    public StrategyRcAdapter(TreeSet<Strategy> mSet) {
        this.mSet = mSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.strategy_rc_item,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Strategy strategy = mSet.pollFirst();
        List<String> pics = strategy.getPicture();
        int  picSize = pics.size();
        holder.viewNumText.setText(String.valueOf(picSize));
        holder.contentText.setText(strategy.getContent());
        List<View> viewList = new ArrayList<>();
        for (int i = 0;i<picSize;i++){
            ImageView picView = new ImageView(mContext);
        }
    }

    @Override
    public int getItemCount() {
        return mSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView orderText;
        private ViewPager itemView;
        private TextView viewNumText;
        private TextView contentText;

        public ViewHolder(View view) {
            super(view);
            orderText = (TextView)view.findViewById(R.id.campus_order);
            itemView = (ViewPager)view.findViewById(R.id.campus_item_view);
            viewNumText = (TextView)view.findViewById(R.id.campus_item_view_num);
            contentText = (TextView)view.findViewById(R.id.campus_item_content);
        }
    }

}
