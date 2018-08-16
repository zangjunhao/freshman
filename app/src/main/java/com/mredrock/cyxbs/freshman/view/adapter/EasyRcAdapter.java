package com.mredrock.cyxbs.freshman.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.activity.DataPatternActivity;

import java.util.List;

public class EasyRcAdapter extends RecyclerView.Adapter<EasyRcAdapter.ViewHolder> {

    private List<String> mList;
    private Context mContext;

    public EasyRcAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_d_rc_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = mList.get(position);
        holder.nameText.setText(name);
        holder.nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DataPatternActivity.class);
                intent.putExtra("name",name);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameText;

        public ViewHolder(View view) {
            super(view);
            nameText = (TextView)view.findViewById(R.id.college_name);
        }
    }
}
