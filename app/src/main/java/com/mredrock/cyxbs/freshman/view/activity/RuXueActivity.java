package com.mredrock.cyxbs.freshman.view.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Describe_1;
import com.mredrock.cyxbs.freshman.presenter.presenter.RuXuePresenter;
import com.mredrock.cyxbs.freshman.view.adapter.NecessaryRcAdapter;
import com.mredrock.cyxbs.freshman.view.view.RuXueView;
import java.util.ArrayList;
import java.util.List;

public class RuXueActivity extends AppCompatActivity implements RuXueView, View.OnClickListener{

    private RuXuePresenter presenter;
    private RecyclerView recyclerView;
    private NecessaryRcAdapter adapter;
    private List<Describe_1> mList = new ArrayList<>();
    private ImageView detaiFunctionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necessary);
        detaiFunctionView = (ImageView)findViewById(R.id.necessary_detail_function);
        recyclerView = (RecyclerView)findViewById(R.id.necessary_rc_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        presenter = new RuXuePresenter(this,this);
        detaiFunctionView.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.necessary_detail_function:
                presenter.showDialog();
                break;
        }
    }
}
