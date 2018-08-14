package com.mredrock.cyxbs.freshman.view.adapter;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Group_code;
import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;

import java.util.List;

public class OnlineRcAdapter extends RecyclerView.Adapter<OnlineRcAdapter.ViewHolder> {


    private List<Group_x_y> mList;
    private List<Group_code> codeList;
    private boolean isCode =false;
    private Context mContext;

    public OnlineRcAdapter(List<Group_x_y> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_rc_item,null);
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
              final Group_code  code = codeList.get(position);
               key = code.getName()+": "+code.getCode();
               isCode = false;
               holder.autoText.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       final Dialog dialog = new Dialog(mContext);
                       TextView textView = new TextView(mContext);
                       textView.setText("复制");
                       LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(50),dip2px(20));
                       dialog.setContentView(textView,layoutParams);
                       dialog.show();
                       textView.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               ClipboardManager clipboardManager = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                               clipboardManager.setPrimaryClip(ClipData.newPlainText("label",code.getCode()));
                               dialog.dismiss();
                           }
                       });
                   }
               });
        }
        holder.autoText.setText(key);
    }
    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
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
