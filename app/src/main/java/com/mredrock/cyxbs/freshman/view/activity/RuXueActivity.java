package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.presenter.presenter.RuXuePresenter;
import com.mredrock.cyxbs.freshman.view.adapter.NecessaryRcAdapter;
import com.mredrock.cyxbs.freshman.view.view.RuXueView;

import java.util.ArrayList;
import java.util.List;

public class RuXueActivity extends AppCompatActivity implements RuXueView {

   public static final String TAG = "RuXueActivity";
    private RuXuePresenter presenter;
    private RecyclerView recyclerView;
    private NecessaryRcAdapter adapter;
    private List<Describe_1> mList = new ArrayList<>();
    private ImageView detaiFunctionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necessary);
        recyclerView = (RecyclerView)findViewById(R.id.necessary_rc_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        presenter = new RuXuePresenter(this,this);
    }

    @Override
    public void describe(Describe_1 describe_1) {
        mList.add(describe_1);
    }

    @Override
    public void onFinish() {
        adapter = new NecessaryRcAdapter(mList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
