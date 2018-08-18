package com.mredrock.cyxbs.freshman.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;

public class Main2Activity extends AppCompatActivity {
    String image1;
    String image2;
    String title;
    String content;
    String buzou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_baodao_activity);
        Intent intent=getIntent();
        image1=intent.getStringExtra("image1");
        image2=intent.getStringExtra("image2");
        title=intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        buzou=intent.getStringExtra("buzou");
        ImageView imageView1=(ImageView)findViewById(R.id.Baodao_image1_1);
        ImageView imageView2=(ImageView)findViewById(R.id.Baodao_image2_1);
        TextView title1=(TextView)findViewById(R.id.BaoDao_activity_title_1);
        final TextView content1=(TextView)findViewById(R.id.BaoDao_activity_content_1);
        TextView buzhou=(TextView)findViewById(R.id.BaoDao_activity_buzou_1);
        ImageView heshang=(ImageView)findViewById(R.id.BaoDao_hes_1);
        Glide.with(this).load(image1).into(imageView1);
        Glide.with(this).load(image2).into(imageView2);
        title1.setText(title);
        content1.setMaxLines(10000);
        content1.setText(content);
        buzhou.setText(buzou);
        heshang.setImageResource(R.drawable.freshman_hes_down);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_baodao_activity_1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        heshang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
                content1.setMaxLines(4);
            }
        });

    }



}
