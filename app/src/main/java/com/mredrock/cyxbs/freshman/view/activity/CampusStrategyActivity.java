package com.mredrock.cyxbs.freshman.view.activity;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.view.adapter.CampusRcAdapter;
import com.mredrock.cyxbs.freshman.view.tool.CampusRcDecoration;

import java.util.List;

public class CampusStrategyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView backImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_strategy);
        recyclerView = (RecyclerView)findViewById(R.id.campus_recycler_view);
        backImage = (ImageView) findViewById(R.id.campus_back);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        CampusRcAdapter adapter = new CampusRcAdapter();
        recyclerView.setAdapter(adapter);
        CampusRcDecoration decoration = new CampusRcDecoration();
        recyclerView.addItemDecoration(decoration);
    }
}
