package com.mredrock.cyxbs.freshman.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Describe;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.zip.Inflater;

public class NecessaryRcAdapter extends RecyclerView.Adapter<NecessaryRcAdapter.ViewHolder>  {

    private List<Describe_1> mList;
    public boolean isDelete = false;
    private OnClickListener onClickListener;
    public int selectedNum = 0;
    private HashMap<Integer,Describe_1> allDescribe_1s = new HashMap<>();
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<Integer> needDeleteList = new ArrayList<>();
    //private  Animation rotateAnimation = new RotateAnimation(0,270,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
    public NecessaryRcAdapter(List<Describe_1> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_necessary,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Describe_1 describe_1 = mList.get(position);
        final ImageView imageView = holder.detailImageView;
        final CheckBox checkBox=holder.nCheckBox;
        final TextView itemTextView=holder.itemTextView;
        final TextView detailTextView=holder.detailTextView;
        allDescribe_1s.put(position,describe_1);
        checkBox.setTag(position);
        itemTextView.setText(describe_1.getName());
        String content = describe_1.getContent();
        if (content.equals("")){
            holder.detailImageView.setVisibility(View.INVISIBLE);
        }else {
            detailTextView.setText(content);
        }
        if (isDelete) {
            checkBox.setSelected(false);
            checkBox.setBackgroundResource(R.drawable.freshman_blue_delete);
        }else {
            checkBox.setBackgroundResource(R.drawable.freshman_blue_rectangle);
        }
        if (checkBoxList!=null&&position<checkBoxList.size()-1){
          //  setItemOrder();
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDelete) {
                    onClickListener.onItemClick(view.isSelected(), describe_1.getName(),holder.getLayoutPosition());
                }
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischeck) {
                if (!isDelete) {
                    int oldPosition = (int)checkBox.getTag();
                    int nowPosition = holder.getLayoutPosition();
                    boolean isSelect = checkBox.isSelected();
                    if (!isSelect) {
                        itemTextView.setTextColor(Color.parseColor("#999999"));
                        itemTextView.setTag(true);
                        detailTextView.setTextColor(Color.parseColor("#999999"));
                        detailTextView.setTag(true);
                        checkBox.setSelected(true);
                        notifyItemMoved(nowPosition,selectedNum);
                        checkBoxList.add(checkBox);
                        selectedNum++;
                    } else {
                        itemTextView.setTextColor(Color.parseColor("#333333"));
                        itemTextView.setTag(false);
                        detailTextView.setTextColor(Color.parseColor("#666666"));
                        detailTextView.setTag(false);
                        checkBox.setSelected(false);
                        notifyItemMoved(nowPosition,oldPosition);
                        checkBoxList.remove(checkBox);
                        selectedNum--;
                    }
                }else {
                    if (ischeck){
                        checkBox.setSelected(true);
                        needDeleteList.add((int)checkBox.getTag());
                    }else {
                        checkBox.setSelected(false);
                        needDeleteList.remove((int)checkBox.getTag());
                    }
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
                   //imageView.startAnimation(rotateAnimation);
               }else {
                  holder.detailTextView.setVisibility(View.GONE);
                imageView.setSelected(false);
                   //imageView.startAnimation(rotateAnimation);
               }
            }
        });
    }

    public void setItemOrder(){
        if (checkBoxList==null)return;
        int size = checkBoxList.size();
        for (int i = 0;i<size;i++){
            CheckBox checkBox = checkBoxList.get(i);
            int oldPosition = (int)checkBox.getTag();
            checkBox.setSelected(true);
            checkBox.setBackgroundResource(R.drawable.freshman_blue_rectangle);
            notifyItemMoved(oldPosition,i);

        }
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
    public void setCheckBackground(){
        if (isDelete) {
            for (int i = 0; i < selectedNum; i++) {
                checkBoxList.get(i).setSelected(false);
                checkBoxList.get(i).setBackgroundResource(R.drawable.freshman_blue_delete);
            }
        }else {
            for (int i = 0; i < selectedNum; i++) {
                checkBoxList.get(i).setSelected(true);
                checkBoxList.get(i).setBackgroundResource(R.drawable.freshman_blue_rectangle);
            }
        }
    }
    public void deleteData(){
       for (int i = 0;i<needDeleteList.size();i++){
           mList.remove(i);
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
        void onItemClick(boolean select,String name,int position);
    }
}

