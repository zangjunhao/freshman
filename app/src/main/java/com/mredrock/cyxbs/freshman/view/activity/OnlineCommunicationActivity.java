package com.mredrock.cyxbs.freshman.view.activity;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Group;
import com.mredrock.cyxbs.freshman.model.convert.Group_x_y;
import com.mredrock.cyxbs.freshman.presenter.presenter.OnlineCommunicationPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.OnlineRcAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.OnlineVpAdapter;
import com.mredrock.cyxbs.freshman.view.tool.MyService;
import com.mredrock.cyxbs.freshman.view.view.OnlineView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineCommunicationActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,OnlineView,View.OnClickListener{

    private OnlineVpAdapter vpAdapter;
    private ViewPager viewPager;
    private List<View> mList = new ArrayList<>();
    private List<Group_x_y> dataList = new ArrayList<>();
    private TextView onlineTab;
    private LinearLayout.LayoutParams layoutParams;
    private int width;
    private RecyclerView[] recyclerViews = new RecyclerView[2];
    private OnlineRcAdapter rcAdapter;
    private EditText[] editTexts = new EditText[2];
    private OnlineCommunicationPresenter presenter;
    private TextView schoolText;
    private TextView homeText;
    private int leftMargin;
    private boolean isAdd = true;
    private LinearLayout[] searchLayouts = new LinearLayout[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_communication);
        MyService.setStatusBar(this);
        viewPager = (ViewPager)findViewById(R.id.online_view_pager);
        onlineTab = (TextView)findViewById(R.id.online_tab);
        schoolText = (TextView)findViewById(R.id.online_name_school);
        homeText = (TextView)findViewById(R.id.online_name_home);
        presenter = new OnlineCommunicationPresenter(this,this);
        schoolText.setOnClickListener(this);
        homeText.setOnClickListener(this);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_online);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initView();
    }

    private void initView(){
        layoutParams =(LinearLayout.LayoutParams) onlineTab.getLayoutParams();
        int tabWidth = layoutParams.width;
        int displayWidth = getWindowManager().getDefaultDisplay().getWidth();
        leftMargin = displayWidth/4-tabWidth/2;
        layoutParams.leftMargin = leftMargin;
        onlineTab.setLayoutParams(layoutParams);
        width = displayWidth/2;
        rcAdapter = new OnlineRcAdapter(dataList);
        addView(0);
        addView(1);
        vpAdapter = new OnlineVpAdapter(mList);
        viewPager.setAdapter(vpAdapter);
        viewPager.setOnPageChangeListener(this);
        rcAdapter.onItemClick(new OnlineRcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name) {
                dataList.clear();
                int currentItem = viewPager.getCurrentItem();
                editTexts[currentItem].setText(name);
                isAdd = false;
            }
        });
    }

    private void addView(final int position){
        View view = LayoutInflater.from(this).inflate(R.layout.online_view_item,null);
        recyclerViews[position] = (RecyclerView)view.findViewById(R.id.online_recycler_view);
        editTexts[position] = (EditText)view.findViewById(R.id.online_auto_complete_text);
        searchLayouts[position] = (LinearLayout)view.findViewById(R.id.online_search_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViews[position].setLayoutManager(layoutManager);
        mList.add(view);
        recyclerViews[position].setAdapter(rcAdapter);
        editTexts[position].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence!=null&&charSequence.length()>0){
                    if (position==0){
                        presenter.getData("学校群",charSequence.toString());
                        editTexts[0].setSelection(editTexts[0].length());
                    }else {
                        presenter.getData("老乡群",charSequence.toString());
                        editTexts[1].setSelection(editTexts[1].length());
                    }
                    dataList.clear();
                }else {
                    dataList.clear();
                    rcAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTexts[position].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    searchLayouts[position].setVisibility(View.INVISIBLE);
                }else {
                    searchLayouts[position].setVisibility(View.VISIBLE);
                    editTexts[position].setText("");
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        layoutParams.leftMargin = leftMargin+(int)((position+positionOffset)*width);
        onlineTab.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {
     //   editTexts[0].clearFocus();
    //    editTexts[1].clearFocus();
        dataList.clear();
        rcAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onGetData(Group_x_y group_x_y) {
        if (dataList.size()!=1||isAdd) {
            dataList.add(group_x_y);
        }
    }

    @Override
    public void onFinish() {
        rcAdapter.notifyDataSetChanged();
        isAdd = true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.online_name_home:
                viewPager.setCurrentItem(1);
                break;
            case R.id.online_name_school:
                viewPager.setCurrentItem(0);
                break;
        }
    }
}
