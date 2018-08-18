package com.mredrock.cyxbs.freshman.view.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NecessaryRcAdapter extends RecyclerView.Adapter<NecessaryRcAdapter.ViewHolder>  {

    private List<Describe_1> mList;
    public boolean isDelete = false;
    public static final int DELETE_F = 0;
    public static final int DELETE_T = 1;
    public static final int UPDATE_T = 2;
    public static final int UPDATE_F = 3;
    public static final int UPDATE_POSTTION = 4;
    public Handler handler = new Handler();
    private OnClickListener onClickListener;
    private int num = 100;
    public int selectedNum = 0;
    private HashSet<CheckBox> checkBoxList = new HashSet<>();
    public NecessaryRcAdapter(List<Describe_1> list) {
       mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_necessary,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
     //   viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position==0){
            selectedNum = 0;
        }
        final Describe_1 describe_1 =mList.get(position);
        final ImageView imageView = holder.detailImageView;
        final CheckBox checkBox=holder.nCheckBox;
        final TextView itemTextView=holder.itemTextView;
        final TextView detailTextView=holder.detailTextView;
        final int number = describe_1.getNumber();
        checkBox.setTag(position);
        itemTextView.setText(describe_1.getName());
        String content = describe_1.getContent();
        if (content==null||content.equals("")){
            holder.detailImageView.setVisibility(View.INVISIBLE);
        }else {
            detailTextView.setText(content);
        }

        if (isDelete){
            if (number<50){
                itemTextView.setTextColor(Color.parseColor("#999999"));
                detailTextView.setTextColor(Color.parseColor("#999999"));
            }else {
                itemTextView.setTextColor(Color.parseColor("#333333"));
                detailTextView.setTextColor(Color.parseColor("#666666"));
            }
            String property = describe_1.getProperty();
            if (property!=null&&property.equals("必需")) {
                checkBox.setVisibility(View.INVISIBLE);
            }else {
                checkBox.setSelected(false);
                checkBox.setBackgroundResource(R.drawable.freshman_blue_delete);
                checkBox.setVisibility(View.VISIBLE);
            }
        }else {
            checkBox.setVisibility(View.VISIBLE);
            if (number<50){
                int oldPosition = describe_1.getOldPosition();
                checkBox.setTag(oldPosition);
                itemTextView.setTextColor(Color.parseColor("#999999"));
                detailTextView.setTextColor(Color.parseColor("#999999"));
                checkBox.setSelected(true);
                checkBoxList.add(checkBox);
            }else {
                itemTextView.setTextColor(Color.parseColor("#333333"));
                detailTextView.setTextColor(Color.parseColor("#666666"));
                checkBox.setSelected(false);
            }
            checkBox.setBackgroundResource(R.drawable.freshman_blue_rectangle);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelect = view.isSelected();
                if (isDelete) {
                    onClickListener.onItemClick(isSelect==true?DELETE_T:DELETE_F, describe_1.getName(),position,0);
                }else {
                    if (isSelect) {
                        onClickListener.onItemClick(UPDATE_T, String.valueOf(describe_1.getId()), selectedNum,(int)checkBox.getTag());
                    }else {
                        onClickListener.onItemClick(UPDATE_F,String.valueOf(describe_1.getId()),num--,0);
                    }
                }
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischeck) {
                if (!isDelete) {
                    selectedNum = checkBoxList.size();
                    int oldPosition = selectedNum;
                    if (oldPosition<=selectedNum){
                       oldPosition = selectedNum;
                    }
                    if (oldPosition==mList.size()){
                        oldPosition--;
                    }
                    int nowPosition = holder.getLayoutPosition();
                    boolean isSelect = checkBox.isSelected();
                    if (!isSelect) {
                        itemTextView.setTextColor(Color.parseColor("#999999"));
                        detailTextView.setTextColor(Color.parseColor("#999999"));
                        checkBox.setSelected(true);
                        notifyItemMoved(nowPosition,selectedNum);
                        describe_1.setNumber(selectedNum+1);
                        checkBoxList.add(checkBox);
                    } else {
                        itemTextView.setTextColor(Color.parseColor("#333333"));
                        detailTextView.setTextColor(Color.parseColor("#666666"));
                        checkBox.setSelected(false);
                        notifyItemMoved(nowPosition,oldPosition);
                        describe_1.setNumber(100+selectedNum);
                        checkBoxList.remove(checkBox);
                    }
                }else {
                    checkBox.setSelected(true);
                }
            }
        });
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

    public void onItemClick(OnClickListener onClickListener){
        if (this.onClickListener==null){
            this.onClickListener = onClickListener;
        }
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
    public interface OnClickListener{
        void onItemClick(int select,String selection,int id,int position);
    }
}

