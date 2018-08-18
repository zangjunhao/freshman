package com.mredrock.cyxbs.freshman.view.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.adapter.MilitaryViewPagerAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.StylePagerAdapter;
import com.mredrock.cyxbs.freshman.view.tool.MyService;
import com.mredrock.cyxbs.freshman.view.tool.ReflexChangeTab;

public class CQUPTStyleActivity extends AppCompatActivity {
    private ReflexChangeTab reflexChangeTab=new ReflexChangeTab();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cquptstyle);
        MyService.setStatusBar(this);
        initview();
    }
    private void initview()
    {
        final Context mcontext=this;
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_style);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.style_tab);
        tabLayout.addTab(tabLayout.newTab().setText("学生组织"));
        tabLayout.addTab(tabLayout.newTab().setText("大型活动"));
        reflexChangeTab.reflex(tabLayout);
        ViewPager viewPager= (ViewPager) findViewById(R.id.style_viewpager);
        viewPager.setAdapter(new StylePagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
