package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;

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
        List<String> pictrues = strategy.getPicture();
        Glide.with(mContext).load(pictrues.get(0)).into(holder.itemView);
        holder.viewNumText.setText(String.valueOf(pictrues.size()));
        holder.contentText.setText(strategy.getContent());
    }

    @Override
    public int getItemCount() {
        return mSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView orderText;
        private ImageView itemView;
        private TextView viewNumText;
        private TextView contentText;

        public ViewHolder(View view) {
            super(view);
            orderText = (TextView)view.findViewById(R.id.campus_order);
            itemView = (ImageView)view.findViewById(R.id.campus_item_view);
            viewNumText = (TextView)view.findViewById(R.id.campus_item_view_num);
            contentText = (TextView)view.findViewById(R.id.campus_item_content);
        }
    }

}
