package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.StudentBedRoomPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.FreshmanPagerAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.StrategyRcAdapter;
import com.mredrock.cyxbs.freshman.view.tool.CampusRcDecoration;
import com.mredrock.cyxbs.freshman.view.tool.MyService;
import com.mredrock.cyxbs.freshman.view.view.BedroomView;

import java.util.ArrayList;
import java.util.List;

public class StudentBedroomActivity extends AppCompatActivity implements BedroomView,View.OnClickListener {


    private LinearLayout nameLayout;
    private ViewPager rcViewPager;
    private int displaySize;
    private StudentBedRoomPresenter presenter;
    private ImageView backImage;
    private StrategyRcAdapter vpAdapter;
    private List<Strategy> mList = new ArrayList<>();
    private String[] names = new String[]{"明理苑","宁静苑","兴业苑","知行苑"};
    private int disPlayWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_bedroom);
        nameLayout = (LinearLayout) findViewById(R.id.bedroom_name_layout);
        rcViewPager= (ViewPager)findViewById(R.id.bedroom_rc_view_pager);
        backImage = (ImageView)findViewById(R.id.bedroom_back);
        displaySize = getWindowManager().getDefaultDisplay().getWidth();
        presenter = new StudentBedRoomPresenter(this,this);
      //  presenter.getbedroomAmount();
        vpAdapter =  new StrategyRcAdapter(mList);
        backImage.setOnClickListener(this);
        initName();
        initViewPager();

    }

    public void initViewPager(){
        presenter.getData(names[0]);
        List<View> viewList = new ArrayList<>();
        for (int i = 0,size = names.length;i<size;i++){
            RecyclerView recyclerView = new RecyclerView(StudentBedroomActivity.this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(vpAdapter);
            recyclerView.addItemDecoration(new CampusRcDecoration());
            viewList.add(recyclerView);
        }
        FreshmanPagerAdapter pagerAdapter = new FreshmanPagerAdapter(viewList);
        rcViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void getBedroomName(List<String> list) {
    //    this.nameList = list;
   //     presenter.getData(list.get(0));
    }

    @Override
    public void getData(Strategy strategy) {
        mList.add(strategy);
    }

    @Override
    public void getDataFinish() {
        vpAdapter.notifyDataSetChanged();
    }

    private void initName(){
        disPlayWidth = MyService.getDisplayWidth(this);
        int size = names.length;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(disPlayWidth/size, LinearLayout.LayoutParams.MATCH_PARENT);
        nameLayout.removeAllViews();
        for (int i = 0;i<size;i++){
            TextView textView = new TextView(this);
            textView.setText(names[i]);
            textView.setGravity(Gravity.CENTER);
            nameLayout.addView(textView,layoutParams);
        }
    }

    @Override
    public void getNameFinish() {
       // initName();
     //   initViewPager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bedroom_back:
                finish();
        }
    }
}
