package com.mredrock.cyxbs.freshman.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/8/16.
 */

public class StyleActivityFragment extends Fragment implements CampusView {
    List<Strategy>list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_style_activity,container,false);
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.activity_recyc);
        
        return view;
    }

    @Override
    public void getData(Strategy strategy) {
        list.add(strategy);
    }

    @Override
    public void onFinish() {

    }
}
