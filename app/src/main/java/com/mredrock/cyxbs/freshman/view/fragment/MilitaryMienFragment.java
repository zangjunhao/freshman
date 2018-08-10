package com.mredrock.cyxbs.freshman.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

/**
 * Created by 67698 on 2018/8/10.
 */

public class MilitaryMienFragment extends Fragment implements JunxunView {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mien,container,false);

        return view;
    }

    @Override
    public void playVidio(String url) {

    }

    @Override
    public void viewImage(String url) {

    }
}
