package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.presenter.presenter.DataDisclosurePresenter;
import com.mredrock.cyxbs.freshman.view.adapter.EasyRcAdapter;
import com.mredrock.cyxbs.freshman.view.tool.RcDecoration;
import com.mredrock.cyxbs.freshman.view.view.DataDView;

import java.util.ArrayList;
import java.util.List;

public class DataDisclosureActivity extends AppCompatActivity implements DataDView, View.OnClickListener{

    private RecyclerView recyclerView;
    private EasyRcAdapter adapter;
    private ImageView backImag;
    private DataDisclosurePresenter presenter;
    private List<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_disclolsure);
        recyclerView = (RecyclerView)findViewById(R.id.data_d_recycler_view);
        RcDecoration decoration = new RcDecoration();
        recyclerView.addItemDecoration(decoration);
        backImag = (ImageView) findViewById(R.id.data_d_back);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        backImag.setOnClickListener(this);
        presenter = new DataDisclosurePresenter(this,this);
        presenter.addData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.data_d_back:
                finish();
        }
    }


    @Override
    public void onData(String name) {
        mList.add(name);
    }

    @Override
    public void onFinish() {
        adapter = new EasyRcAdapter(mList);
        recyclerView.setAdapter(adapter);
    }
}
