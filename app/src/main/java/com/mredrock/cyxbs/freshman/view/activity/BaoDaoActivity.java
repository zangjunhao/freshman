package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.CampusStrategyPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.BaoDaoAdapter;
import com.mredrock.cyxbs.freshman.view.tool.MyService;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.ArrayList;
import java.util.List;

public class BaoDaoActivity extends AppCompatActivity implements CampusView {

    List<Strategy> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_dao);
        MyService.setStatusBar(this);
        CampusStrategyPresenter campusStrategyPresenter=new CampusStrategyPresenter(this,this);
        campusStrategyPresenter.addData("报道流程",1,10);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.BaoDao_rec);
        recyclerView.setAdapter(new BaoDaoAdapter(list,this,this));
        final LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_baodao);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void getData(Strategy strategy) {
        list.add(strategy);
    }

    @Override
    public void onFinish() {

    }
}
