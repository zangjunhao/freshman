package com.mredrock.cyxbs.freshman.view.adapter;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Group;
import com.mredrock.cyxbs.freshman.model.convert.Group_code;
import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;

import java.util.List;

public class OnlineRcAdapter extends RecyclerView.Adapter<OnlineRcAdapter.ViewHolder> {


    private List<Group> mList;
    private List<Group_code> codeList;
    private Context mContext;
    private OnItemClickListener listener;

    public OnlineRcAdapter(List<Group> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_rc_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Group group = mList.get(position);
        if (group.getContent()instanceof Group_x_y){
            holder.autoText.setText(group.getN());
            holder.autoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick((Group_x_y) group.getContent());
                }
            });
        }else if (group.getContent() instanceof String){
            holder.autoText.setText(group.getN()+(String)group.getContent());
        }
    }

    private void addDialog(final Group_code code){
        final Dialog dialog = new Dialog(mContext);
        TextView textView = new TextView(mContext);
        textView.setText("复制");
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(50),dip2px(20));
        dialog.setContentView(textView,layoutParams);
        dialog.show();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText("label",code.getCode()));
                Toast.makeText(mContext,"复制成功",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    public void setCodeList(List<Group_code> codes){
        codeList = codes;
    }
    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
    public void onItemClick(OnItemClickListener listener){
        if (listener==null){
            this.listener = listener;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView autoText;
        public ViewHolder(View itemView) {
            super(itemView);
            autoText = (TextView)itemView.findViewById(R.id.online_rc_text);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(Group_x_y group_x_y);
    }
}
