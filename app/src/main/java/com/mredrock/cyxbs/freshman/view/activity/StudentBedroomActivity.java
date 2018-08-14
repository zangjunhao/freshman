package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.StudentBedRoomPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.EasyRcAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.FreshmanPagerAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.StrategyRcAdapter;
import com.mredrock.cyxbs.freshman.view.view.BedroomView;

import java.util.ArrayList;
import java.util.List;

public class StudentBedroomActivity extends AppCompatActivity implements BedroomView,View.OnClickListener {

    private List<String> nameList;
    private RecyclerView nameRcView;
    private ViewPager rcViewPager;
    private int displaySize;
    private StudentBedRoomPresenter presenter;
    private ImageView backImage;
    private StrategyRcAdapter vpAdapter;
    private List<Strategy> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_bedroom);
        nameRcView = (RecyclerView)findViewById(R.id.bedroom_name_rc_view);
        rcViewPager= (ViewPager)findViewById(R.id.bedroom_rc_view_pager);
        backImage = (ImageView)findViewById(R.id.bedroom_back);
        displaySize = getWindowManager().getDefaultDisplay().getWidth();
        presenter = new StudentBedRoomPresenter(this,this);
        presenter.getbedroomAmount();
        vpAdapter =  new StrategyRcAdapter(mList);
        backImage.setOnClickListener(this);
    }

    public void initViewPager(){
        List<View> viewList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        for (int i = 0,size = nameList.size();i<size;i++){
            RecyclerView recyclerView = new RecyclerView(StudentBedroomActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(vpAdapter);
            viewList.add(recyclerView);
        }
        FreshmanPagerAdapter pagerAdapter = new FreshmanPagerAdapter(viewList);
        rcViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void getBedroomName(List<String> list) {
        this.nameList = list;
        presenter.getData(list.get(0));
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
        int size = nameList.size();
        EasyRcAdapter NameAdapter = new EasyRcAdapter(nameList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        nameRcView.setLayoutManager(linearLayoutManager);
        nameRcView.setAdapter(NameAdapter);
        backImage.setOnClickListener(this);
    }

    @Override
    public void getNameFinish() {
        initName();
        initViewPager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bedroom_back:
                finish();
        }
    }
}
