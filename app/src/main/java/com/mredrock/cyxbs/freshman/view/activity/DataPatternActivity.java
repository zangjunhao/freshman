package com.mredrock.cyxbs.freshman.view.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.BelowSubject;
import com.mredrock.cyxbs.freshman.presenter.presenter.HardSubjectPresenter;
import com.mredrock.cyxbs.freshman.view.CustomView.BarView;
import com.mredrock.cyxbs.freshman.view.CustomView.RoundView;
import com.mredrock.cyxbs.freshman.view.adapter.FreshmanPagerAdapter;
import com.mredrock.cyxbs.freshman.view.view.PatternView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataPatternActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener,PatternView {

    private ViewPager viewPager;
    private List<View> mList = new ArrayList<>();
    private TextView onlineTab;
    private LinearLayout.LayoutParams layoutParams;
    private int width;
    private TextView proportionText;
    private TextView subjectText;
    private ImageView backImage;
    private int leftMargin;
    private int data[] = new int[3];
    private String[] names = new String[3];
    private RoundView roundView;
    private BarView barView;
    private  FrameLayout hardLayout;
    private HardSubjectPresenter presenter;
    private int women;
    private int men;
    private TextView hardTitleText;
    private TextView pTitleText;
    private String name;
    private int duraringMillis = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pattern);
        viewPager = (ViewPager)findViewById(R.id.pattern_view_pager);
        onlineTab = (TextView)findViewById(R.id.pattern_tab);
        proportionText = (TextView)findViewById(R.id.pattern_proportion);
        subjectText = (TextView)findViewById(R.id.pattern_hard_subject);
        backImage = (ImageView)findViewById(R.id.pattern_back);
        presenter = new HardSubjectPresenter(this,this);
         name = getIntent().getStringExtra("name");
        presenter.getData(name);
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
        proportionText.setOnClickListener(this);
        subjectText.setOnClickListener(this);
        backImage.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
    }

    private void addView() {
        roundView = new RoundView(this,men,women,duraringMillis);
        View view = LayoutInflater.from(this).inflate(R.layout.proportion_layout,null);
        FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.proportion_view_layout);
        frameLayout.addView(roundView);
        pTitleText = (TextView)view.findViewById(R.id.proportion_title);
        mList.add(view);
        View view1 = LayoutInflater.from(this).inflate(R.layout.hard_subject_layout,null);
         hardLayout  = (FrameLayout)view1.findViewById(R.id.hard_subject_view_layout);
         hardTitleText = (TextView)view1.findViewById(R.id.proportion_hard_title);
        mList.add(view1);
        roundView.startAnimator();
        pTitleText.setText(name+"男女比例");
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        layoutParams.leftMargin = leftMargin+(int)((position+positionOffset)*width);
        onlineTab.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {
        if (position==0){
           roundView.startAnimator();
           proportionText.setTextColor(Color.parseColor("#54ACFF"));
           subjectText.setTextColor(Color.parseColor("#999999"));
            pTitleText.setText(name+"男女比例");
        }else {
            if (hardLayout.getChildCount()==0){
                hardLayout.addView(barView);
            }
            barView.startAnimator();
            subjectText.setTextColor(Color.parseColor("#54ACFF"));
            proportionText.setTextColor(Color.parseColor("#999999"));
            hardTitleText.setText("2017-2018学年第二学期挂科情况");
        }
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

    @Override
    public void getData(List<BelowSubject> list) {
        Collections.sort(list, new Comparator<BelowSubject>() {
            @Override
            public int compare(BelowSubject belowSubject, BelowSubject t1) {
                if (belowSubject.getId()>t1.getId()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        for (int i = 0;i<3;i++){
            BelowSubject belowSubject = list.get(i);
            data[i] = belowSubject.getBelow_amount();
            names[i] = belowSubject.getSubject_name();
        }
    }

    @Override
    public void onFinish() {
       barView = new BarView(this,data,names,duraringMillis);
    }

    @Override
    public void getProportion(int men, int women) {
        this.men = men;
        this.women = women;
    }

    @Override
    public void getProportionFinish() {
        addView();
        FreshmanPagerAdapter adapter = new FreshmanPagerAdapter(mList);
        viewPager.setAdapter(adapter);
    }
}

