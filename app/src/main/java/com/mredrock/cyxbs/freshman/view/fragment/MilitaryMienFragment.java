package com.mredrock.cyxbs.freshman.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.view.view.JunxunView;

/**
 * Created by 67698 on 2018/8/10.
 */

public class MilitaryMienFragment extends Fragment implements JunxunView {
    private RecyclerView PhotoList;
    private RecyclerView VideoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mien,container,false);
        PhotoList=(RecyclerView)view.findViewById(R.id.junxunPhotolist);
        VideoList=(RecyclerView)view.findViewById(R.id.junxunVideolist);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        PhotoList.setLayoutManager(linearLayoutManager);
        VideoList.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void playVidio(String url) {

    }

    @Override
    public void viewImage(String url) {

    }
}
