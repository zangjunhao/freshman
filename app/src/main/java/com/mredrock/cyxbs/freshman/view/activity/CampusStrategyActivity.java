package com.mredrock.cyxbs.freshman.view.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.adapter.CampusRcAdapter;
import com.mredrock.cyxbs.freshman.view.tool.RcDecoration;
import com.mredrock.cyxbs.freshman.view.tool.MyService;

public class CampusStrategyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView backImage;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_strategy);
        recyclerView = (RecyclerView)findViewById(R.id.campus_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        CampusRcAdapter adapter = new CampusRcAdapter();
        recyclerView.setAdapter(adapter);
        RcDecoration decoration = new RcDecoration();
        decoration.setLeft(5);
        recyclerView.addItemDecoration(decoration);
        MyService.setStatusBar(this);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_gonglue);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
