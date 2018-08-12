package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.CampusStrategyPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.NecessaryRcAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.StrategyRcAdapter;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class StrategyActivity extends AppCompatActivity implements CampusView, View.OnClickListener{

    private List<Strategy> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StrategyRcAdapter adapter;
    private CampusStrategyPresenter presenter;
    private TextView labelText;
    private ImageView backImag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stategy);
        recyclerView = (RecyclerView)findViewById(R.id.strategy_recycler_view);
        labelText = (TextView)findViewById(R.id.strategy_name);
        backImag = (ImageView)findViewById(R.id.strategy_back);
        backImag.setOnClickListener(this);
       init();
    }
    private void init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        String name = getIntent().getStringExtra("name");
        labelText.setText(name);
        presenter  = new CampusStrategyPresenter(this,this);
        presenter.addData(name,1,10);
    }

    @Override
    public void getData(Strategy strategy) {
        mList.add(strategy);
    }

    @Override
    public void onFinish() {
        if (adapter==null){
            adapter = new StrategyRcAdapter(mList);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.strategy_back:
                finish();
                break;
        }
    }
}
