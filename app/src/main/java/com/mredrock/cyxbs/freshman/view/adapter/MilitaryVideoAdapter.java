package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/8/11.
 */

public class MilitaryVideoAdapter extends RecyclerView.Adapter<MilitaryVideoAdapter.MilitaryVideoViewHolder> {
    private  int[] imageResourse={
            R.drawable.freshman_2015_1,
            R.drawable.freshmen_2015_2,
            R.drawable.freshman_2016_1,
            R.drawable.freshman_2016_2,
            R.drawable.freshman_2017
    };

    private String[] VideoName={
            "2016重庆邮电大学军训视频2",
            "2016重庆邮电大学军训视频",
            "2017重庆邮电大学军训视频",
            "2015重庆邮电大学军训视频1",
            "2015重庆邮电大学军训视频2"
    };

    private String[] VideoUrl={
            "https://v.qq.com/x/page/r07539i9p1d.html?",
            "https://v.qq.com/x/page/x07539j9z3q.html?",
            "https://v.qq.com/x/page/s075342qfmp.html?",
            "https://v.qq.com/x/page/e0753cjr07c.html?",
            "https://v.qq.com/x/page/w0753xhknmf.html?"
    };
    private Context context;
    @Override
    public MilitaryVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_video_recyc,parent,false);
        return new MilitaryVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MilitaryVideoViewHolder holder, final int position) {
        ImageView fengmian=holder.imageView;
        TextView textView=holder.textView;
        CardView cardView=holder.cardView;
        fengmian.setImageResource(imageResourse[position]);
        textView.setText(VideoName[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(VideoUrl[position]);
                intent.setData(content_url);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MilitaryVideoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;
        public MilitaryVideoViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.video_card);
            imageView=(ImageView)itemView.findViewById(R.id.video_fengmian);
            textView=(TextView)itemView.findViewById(R.id.video_name);
        }
    }
}
