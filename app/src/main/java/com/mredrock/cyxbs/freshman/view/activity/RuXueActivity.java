package com.mredrock.cyxbs.freshman.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.presenter.presenter.RuXuePresenter;
import com.mredrock.cyxbs.freshman.view.view.RuXueView;

public class RuXueActivity extends AppCompatActivity implements RuXueView {

   public static final String TAG = "RuXueActivity";
    private RuXuePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ru_xue);
        presenter = new RuXuePresenter(this,this);
    }

    @Override
    public void describe(int id, String name, String content) {
        Log.d(TAG,content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
