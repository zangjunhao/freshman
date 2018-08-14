package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
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
import com.mredrock.cyxbs.freshman.view.activity.DataDisclosureActivity;
import com.mredrock.cyxbs.freshman.view.activity.StrategyActivity;
import com.mredrock.cyxbs.freshman.view.activity.StudentBedroomActivity;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import org.w3c.dom.Text;

import java.util.List;

public class CampusRcAdapter extends RecyclerView.Adapter<CampusRcAdapter.ViewHolder> {
    public static final String[] ARRAY = new String[]{"学生食堂","学生寝室","周边美食","附近景点","校园环境","数据揭秘","附近银行","公交线路","快递收发"};
    public static final int[] ICONS = new int[]{R.drawable.freshman_cafeteria,R.drawable.freshman_bedroom,R.drawable.freshman_food,R.drawable.freshman_view,
            R.drawable.freshman_campus,R.drawable.freshman_data,R.drawable.freshman_bank,R.drawable.freshman_bus,R.drawable.freshman_delivery};
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
        final String name = ARRAY[position];
        holder.itemText.setText(name);
        holder.itemView.setBackgroundResource(ICONS[position]);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switch (name) {
                    case "数据揭秘":
                        intent = new Intent(mContext, DataDisclosureActivity.class);
                        break;
                    case "学生寝室":
                        intent = new Intent(mContext, StudentBedroomActivity.class);
                        break;
                        default:
                         intent = new Intent(mContext, StrategyActivity.class);
                            intent.putExtra("name", name);
                         break;

                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ARRAY.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemView;
        private TextView itemText;
        private CardView itemLayout;

        public ViewHolder(View view) {
            super(view);
            itemView = (ImageView)view.findViewById(R.id.campus_item_view);
            itemText = (TextView)view.findViewById(R.id.campus_item_text);
            itemLayout = (CardView) view.findViewById(R.id.campus_item_layout);
        }
    }
}
