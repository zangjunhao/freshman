package com.mredrock.cyxbs.freshman.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/8/16.
 */

public class OrganizationRecAdapter extends RecyclerView.Adapter<OrganizationRecAdapter.OranizationRecViewHolder>  {
    private List<Strategy> mlist=new ArrayList<>();
    private Context mcontext;
    public OrganizationRecAdapter(List<Strategy> mlist, Context mcontext)
    {
        this.mlist=mlist;
        this.mcontext=mcontext;
    }

    @Override
    public OranizationRecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.item_style_organization_rec,parent,false);
        return new OranizationRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OranizationRecViewHolder holder, int position) {
            final TextView textView=holder.textView;
            textView.setText(mlist.get(position).getName());
            CardView cardView=holder.cardView;

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    class OranizationRecViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public OranizationRecViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.organization_name);
            cardView=(CardView)itemView.findViewById(R.id.organization_card);
        }
    }
}
