package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.CampusStrategy;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.CampusStrategyPresenter;
import com.mredrock.cyxbs.freshman.view.activity.StrategyActivity;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import org.w3c.dom.Text;

import java.util.List;

public class CampusRcAdapter extends RecyclerView.Adapter<CampusRcAdapter.ViewHolder> {
    public String[] array = new String[]{"学生食堂","周边美食","附近景点","校园环境","附近银行","公交线路","快递收发","大型活动","报道流程","我想对你说","学生组织"};
    private Context mContext;

    public CampusRcAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.campus_rc_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = array[position];
        holder.itemText.setText(name);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(mContext, StrategyActivity.class);
                intent.putExtra("name",name);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemView;
        private TextView itemText;
        private RelativeLayout itemLayout;

        public ViewHolder(View view) {
            super(view);
            itemView = (ImageView)view.findViewById(R.id.campus_item_view);
            itemText = (TextView)view.findViewById(R.id.campus_item_text);
            itemLayout = (RelativeLayout)view.findViewById(R.id.campus_item_layout);
        }
    }
}
