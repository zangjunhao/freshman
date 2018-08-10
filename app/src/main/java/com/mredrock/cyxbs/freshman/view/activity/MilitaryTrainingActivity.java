package com.mredrock.cyxbs.freshman.view.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.adapter.MilitaryViewPagerAdapter;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

public class MilitaryTrainingActivity extends AppCompatActivity  {

    private String TAG="MilitaryTrainingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military_training);
        initview();
    }

    private void initview()
    {
     
        final Context mcontext=this;
        TabLayout tabLayout=(TabLayout)findViewById(R.id.military_tab);
        tabLayout.addTab(tabLayout.newTab().setText("军训风采"));
        tabLayout.addTab(tabLayout.newTab().setText("小贴士"));
        ViewPager viewPager= (ViewPager) findViewById(R.id.military_viewpager);
        viewPager.setAdapter(new MilitaryViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }
}
