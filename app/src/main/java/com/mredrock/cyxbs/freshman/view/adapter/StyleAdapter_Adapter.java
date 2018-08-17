package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.List;

/**
 * Created by 67698 on 2018/8/17.
 */

public class StyleAdapter_Adapter extends RecyclerView.Adapter<StyleAdapter_Adapter.StyleAdapter_AdapterViewHolder>{

    List<String> imageurl;
    Context context;
    public StyleAdapter_Adapter(List<String> imageurl,Context context)
    {
        this.imageurl=imageurl;
        this.context=context;
    }
    @Override
    public StyleAdapter_AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StyleAdapter_AdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_style_activity_rec,parent,false));
    }

    @Override
    public void onBindViewHolder(StyleAdapter_AdapterViewHolder holder, int position) {
            ImageView imageView=holder.imageView;
        Glide.with(context).load("http://47.106.33.112:8080/welcome2018"+imageurl.get(position)).into(imageView);
    }

    @Override
    public int getItemCount() {
        return imageurl.size();
    }

    class StyleAdapter_AdapterViewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        public StyleAdapter_AdapterViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.style_activity_rec_image);
        }
    }
}
