package com.mredrock.cyxbs.freshman.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;

import java.util.List;
import java.util.zip.Inflater;

public class NecessaryRcAdapter extends RecyclerView.Adapter<NecessaryRcAdapter.ViewHolder> {

    private List<Describe_1> mList;

    public NecessaryRcAdapter(List<Describe_1> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_necessary,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Describe_1 describe_1 = mList.get(position);
        holder.itemTextView.setText(describe_1.getName());
        holder.detailTextView.setText(describe_1.getContent());
        final ImageView imageView = holder.detailImageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isSelected = imageView.isSelected();
               if (!isSelected){
                 holder.detailTextView.setVisibility(View.VISIBLE);
                  imageView.setSelected(true);
               }else {
                  holder.detailTextView.setVisibility(View.GONE);
                   imageView.setSelected(false);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox nCheckBox;
        private TextView itemTextView;
        private ImageView detailImageView;
        private TextView detailTextView;

        public ViewHolder(View v) {
            super(v);
            nCheckBox = (CheckBox)v.findViewById(R.id.necessary_checkbox);
            itemTextView = (TextView)v.findViewById(R.id.necessary_item);
            detailImageView = (ImageView)v.findViewById(R.id.necessary_detail_view);
            detailTextView = (TextView)v.findViewById(R.id.necessary_detail);
        }
    }
}
