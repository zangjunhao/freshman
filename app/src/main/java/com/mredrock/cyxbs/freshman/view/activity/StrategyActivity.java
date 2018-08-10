package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.CampusStrategyPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.StrategyRcAdapter;
import com.mredrock.cyxbs.freshman.view.view.CampusView;
import java.util.TreeSet;

public class StrategyActivity extends AppCompatActivity implements CampusView{

    private TreeSet<Strategy> mSet = new TreeSet<>();
    private RecyclerView recyclerView;
    private StrategyRcAdapter adapter;
    private CampusStrategyPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stategy);
        recyclerView = (RecyclerView)findViewById(R.id.strategy_recycler_view);
       init();
    }
    private void init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        String name = getIntent().getStringExtra("name");
        presenter  = new CampusStrategyPresenter(this,this);
        presenter.getData(name,1,1);
    }

    @Override
    public void getData(Strategy strategy) {
        mSet.add(strategy);
    }

    @Override
    public void onFinish() {
        if (adapter==null){
            adapter = new StrategyRcAdapter(mSet);
        }
        recyclerView.setAdapter(adapter);

    }
}
