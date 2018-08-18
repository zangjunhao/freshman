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
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.zip.Inflater;

public class NecessaryRcAdapter extends RecyclerView.Adapter<NecessaryRcAdapter.ViewHolder>  {

    private List<Describe_1> mList;
    public boolean isDelete = false;
    public static final int DELETE_F = 0;
    public static final int DELETE_T = 1;
    public static final int UPDATE_T = 2;
    public static final int UPDATE_F = 3;
    public static final int UPDATE_POSTTION = 4;
    private OnClickListener onClickListener;
    private int num = 100;
    public int selectedNum = 0;
    private HashMap<Integer,Describe_1> allDescribe_1s = new HashMap<>();
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<Integer> needDeleteList = new ArrayList<>();
    private Iterator<Describe_1> iterable;
    //private  Animation rotateAnimation = new RotateAnimation(0,270,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
    public NecessaryRcAdapter(List<Describe_1> list) {
       mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_necessary,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setIsRecyclable(false);
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
            if (number<50&&number!=0){
                selectedNum++;
                int oldPosition = describe_1.getOldPosition();
                checkBox.setTag(oldPosition);
                itemTextView.setTextColor(Color.parseColor("#999999"));
                detailTextView.setTextColor(Color.parseColor("#999999"));
                checkBox.setSelected(true);
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
                    int oldPosition = (int)checkBox.getTag();
                    if (oldPosition<=selectedNum){
                       oldPosition = selectedNum+1;
                    }
                    int nowPosition = holder.getLayoutPosition();
                    boolean isSelect = checkBox.isSelected();
                    if (!isSelect) {
                        itemTextView.setTextColor(Color.parseColor("#999999"));
                        detailTextView.setTextColor(Color.parseColor("#999999"));
                        checkBox.setSelected(true);
                        notifyItemMoved(nowPosition,selectedNum);
                        checkBoxList.add(checkBox);
                        selectedNum++;
                    } else {
                        itemTextView.setTextColor(Color.parseColor("#333333"));
                        detailTextView.setTextColor(Color.parseColor("#666666"));
                        checkBox.setSelected(false);
                        notifyItemMoved(nowPosition,oldPosition);
                        checkBoxList.remove(checkBox);
                        selectedNum--;
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

