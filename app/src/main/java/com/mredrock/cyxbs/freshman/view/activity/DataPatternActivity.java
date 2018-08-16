package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.CustomView.BarView;
import com.mredrock.cyxbs.freshman.view.adapter.FreshmanPagerAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.OnlineRcAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.OnlineVpAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataPatternActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{

    private ViewPager viewPager;
    private List<View> mList = new ArrayList<>();
    private TextView onlineTab;
    private LinearLayout.LayoutParams layoutParams;
    private int width;
    private TextView proportionText;
    private TextView subjectText;
    private ImageView backImage;
    private int leftMargin;
    private int data[] = new int[]{101,87,84};
    private String[] names = new String[]{"大学物理","数据结构","高等数学"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pattern);
        viewPager = (ViewPager)findViewById(R.id.pattern_view_pager);
        onlineTab = (TextView)findViewById(R.id.pattern_tab);
        proportionText = (TextView)findViewById(R.id.pattern_proportion);
        subjectText = (TextView)findViewById(R.id.pattern_hard_subject);
        backImage = (ImageView)findViewById(R.id.pattern_back);
        proportionText.setOnClickListener(this);
        subjectText.setOnClickListener(this);
        backImage.setOnClickListener(this);
        initView();
    }

    private void initView(){
        layoutParams =(LinearLayout.LayoutParams) onlineTab.getLayoutParams();
        int tabWidth = layoutParams.width;
        int displayWidth = getWindowManager().getDefaultDisplay().getWidth();
        leftMargin = displayWidth/4-tabWidth/2;
        layoutParams.leftMargin = leftMargin;
        onlineTab.setLayoutParams(layoutParams);
        width = displayWidth/2;
        addView(0);
        FreshmanPagerAdapter adapter = new FreshmanPagerAdapter(mList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void addView(final int position) {
        BarView barView = new BarView(this,data,names,2000);
        mList.add(barView);
        barView.startAnimator();
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        layoutParams.leftMargin = leftMargin+(int)((position+positionOffset)*width);
        onlineTab.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pattern_hard_subject:
                viewPager.setCurrentItem(1);
                break;
            case R.id.pattern_proportion:
                viewPager.setCurrentItem(0);
                break;
            case R.id.pattern_back:
                finish();
                break;
        }
    }
}

