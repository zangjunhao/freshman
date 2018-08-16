package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/8/17.
 */

public class StyleActivityAdapter extends RecyclerView.Adapter<StyleActivityAdapter.StyleActivityViewHolder> {
    List<Strategy>list=new ArrayList<>();
    Context context;
   public StyleActivityAdapter(List<Strategy>list, Context context)
   {
       this.list=list;
       this.context=context;
   }

    @Override
    public StyleActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_style_activity,parent,false);
        return new StyleActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StyleActivityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StyleActivityViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        RecyclerView recyclerView;
        public StyleActivityViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.style_activity_title);
            content=(TextView)itemView.findViewById(R.id.style_activity_content);
            recyclerView=(RecyclerView) itemView.findViewById(R.id.style_activity_image);
        }
    }
}
