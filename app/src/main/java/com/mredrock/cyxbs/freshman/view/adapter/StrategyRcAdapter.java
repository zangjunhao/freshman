package com.mredrock.cyxbs.freshman.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.view.tool.ImageTool;
import com.mredrock.cyxbs.freshman.view.tool.MyService;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class StrategyRcAdapter extends RecyclerView.Adapter<StrategyRcAdapter.ViewHolder>implements View.OnClickListener {

    private List<Strategy> mList;
    private Context mContext;
    public boolean isFood = false;
    public StrategyRcAdapter(List<Strategy> list) {
       mList = list;
    }
    public boolean isAnotherLayout = false;
    public static final int ANOTHER_LAYOUT = 1;
    public static final int NOMAL_LAYOUT = 0;
    private int displayWidth;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        mContext = parent.getContext();
        displayWidth = MyService.getDisplayWidth(mContext);
        if (viewType==NOMAL_LAYOUT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.strategy_rc_item, parent, false);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.strategy_another_rc_item,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Strategy strategy = mList.get(position);
        List<String> pics = strategy.getPicture();
        String name = strategy.getName();
        String content = strategy.getContent();
        if (isAnotherLayout){
            holder.anotherContentText.setText(content);
            holder.anotherNameText.setText(name);
            ImageView imageView = holder.anotherImage;
            final String pic = pics.get(0);
            Glide.with(mContext).load("http://47.106.33.112:8080/welcome2018"+pic).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(mContext);
                    LinearLayout.LayoutParams params =
                            new LinearLayout.LayoutParams(displayWidth,displayWidth/2);
                    ImageView imageView1 = new ImageView(mContext);
                    imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(mContext).load("http://47.106.33.112:8080/welcome2018"+pic).into(imageView1);
                    dialog.setContentView(imageView1,params);
                    dialog.show();
                    imageView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            });
        }else {
            if (isFood) {
                holder.viewNumText.setVisibility(View.VISIBLE);
                holder.viewNumText.setText(String.valueOf(position + 1));
            } else {
                holder.viewNumText.setVisibility(View.GONE);
            }
            holder.contentText.setText(content);
            holder.itemNameText.setText(name);
            if (isAnotherLayout) {
            }
            initView(holder, pics);
        }
    }

    private void initView(final ViewHolder holder, final List<String> pic){
        int indicatorSize = dip2px(7);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(indicatorSize,indicatorSize);
        layoutParams.leftMargin = dip2px(5);
        layoutParams.rightMargin = dip2px(5);
        List<View> imageViewList = new ArrayList<>();
        imageViewList.clear();
        final ViewPager viewPager = holder.itemView;
        viewPager.removeAllViews();
        final LinearLayout indicatorLayout = holder.indicatorLayout;
        indicatorLayout.removeAllViews();
        int size = pic.size();
        for (int i = 0;i<size;i++){
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(mContext).load("http://47.106.33.112:8080/welcome2018"+pic.get(i)).into(imageView);
            imageViewList.add(imageView);
            Button button = new Button(mContext);
            button.setBackgroundResource(R.drawable.freshman_indicator_unselect);
            holder.indicatorLayout.addView(button,layoutParams);
            final int j = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(mContext,R.style.freshman_AlertDialog_style);
                    List<View> views = new ArrayList<>();
                    ViewPager pager = new ViewPager(mContext);
                    for (int k = 0;k<pic.size();k++){
                        ImageView imageView1 =(ImageView) LayoutInflater.from(mContext).inflate(R.layout.single_image_view,null);
                        Glide.with(mContext).load("http://47.106.33.112:8080/welcome2018"+pic.get(k)).into(imageView1);
                        views.add(imageView1);
                    }
                  FreshmanPagerAdapter adapter = new FreshmanPagerAdapter(views);
                    pager.setAdapter(adapter);
                    LinearLayout.LayoutParams params =
                            new LinearLayout.LayoutParams(displayWidth,displayWidth/2);
                    dialog.setContentView(pager,params);
                    dialog.show();
                    pager.setCurrentItem(j);
                    pager.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            });
            if (i>2){
                break;
            }
        }
        FreshmanPagerAdapter adapter = new FreshmanPagerAdapter(imageViewList);
        viewPager.setAdapter(adapter);
        if (indicatorLayout!=null&&indicatorLayout.getChildCount()>0)
        indicatorLayout.getChildAt(0).setBackgroundResource(R.drawable.freshman_indicator_select);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0;i<holder.itemView.getChildCount();i++){
                    if (position!=i) {
                       indicatorLayout.getChildAt(i).setBackgroundResource(R.drawable.freshman_indicator_unselect);
                    }else {
                        indicatorLayout.getChildAt(position).setBackgroundResource(R.drawable.freshman_indicator_select);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public  int dip2px(int dp)
    {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isAnotherLayout){
            return ANOTHER_LAYOUT;
        }else {
            return NOMAL_LAYOUT;
        }
    }

    @Override
    public void onClick(View view) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ViewPager itemView;
        private TextView viewNumText;
        private TextView contentText;
        private LinearLayout indicatorLayout;
        private TextView itemNameText;
        private ImageView anotherImage;
        private TextView anotherNameText;
        private TextView anotherContentText;

        public ViewHolder(View view) {
            super(view);
            itemView = (ViewPager)view.findViewById(R.id.campus_item_view);
            viewNumText = (TextView)view.findViewById(R.id.campus_item_view_num);
            contentText = (TextView)view.findViewById(R.id.campus_item_content);
            indicatorLayout = (LinearLayout)view.findViewById(R.id.campus_indicator_layout);
            itemNameText = (TextView)view.findViewById(R.id.campus_item_name);
            anotherImage = (ImageView)view.findViewById(R.id.strategy_image_view);
            anotherNameText = (TextView)view.findViewById(R.id.strategy_item_name);
            anotherContentText= (TextView)view.findViewById(R.id.strategy_item_content);
        }
    }

}
