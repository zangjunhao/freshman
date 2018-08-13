package com.mredrock.cyxbs.freshman.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Group_code;
import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;

import java.util.List;

public class OnlineRcAdapter extends RecyclerView.Adapter<OnlineRcAdapter.ViewHolder> {


    private List<Group_x_y> mList;
    private List<Group_code> codeList;
    private boolean isCode =false;

    public OnlineRcAdapter(List<Group_x_y> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_rc_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String key = null;
        if (!isCode) {
            final Group_x_y group = mList.get(position);
             key = group.getName();
             holder.autoText.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     codeList = group.getArray1();
                     isCode = true;
                     notifyDataSetChanged();
                 }
             });
        }else {
              Group_code  code = codeList.get(position);
               key = code.getName()+": "+code.getCode();
               isCode = false;
               holder.autoText.setOnClickListener(null);
        }
        holder.autoText.setText(key);
    }

    @Override
    public int getItemCount() {
        return isCode?codeList.size():mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView autoText;
        public ViewHolder(View itemView) {
            super(itemView);
            autoText = (TextView)itemView.findViewById(R.id.online_rc_text);
        }
    }
}
