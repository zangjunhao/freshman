package com.mredrock.cyxbs.freshman.view.activity;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private FloatingActionButton floatingActionButton;
    private RelativeLayout addLayout;
    private EditText inputView;
    private Button addButton;
    private TextView editTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necessary);
        detaiFunctionView = (ImageView)findViewById(R.id.necessary_detail_function);
        recyclerView = (RecyclerView)findViewById(R.id.necessary_rc_view);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.necessary_float_button);
        addLayout = (RelativeLayout)findViewById(R.id.necessary_add_layout);
        inputView = (EditText)findViewById(R.id.necessary_add_input);
        addButton = (Button)findViewById(R.id.necessary_add_button);
        editTextView = (TextView)findViewById(R.id.necessary_edit);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        presenter = new RuXuePresenter(this,this);
        detaiFunctionView.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
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
            case R.id.necessary_float_button:
                addLayout.setVisibility(View.VISIBLE);
                floatingActionButton.hide();
                addButton.setOnClickListener(this);
                break;
            case R.id.necessary_add_button:
                presenter.addContent(inputView.getText().toString());
                adapter.notifyDataSetChanged();
                inputView.setText("");
                addLayout.setVisibility(View.GONE);
                floatingActionButton.show();

        }
    }
}
