package com.mredrock.cyxbs.freshman.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.presenter.presenter.CampusStrategyPresenter;
import com.mredrock.cyxbs.freshman.view.adapter.OrganizationRecAdapter;
import com.mredrock.cyxbs.freshman.view.adapter.StyleOrganPagerAdapter;
import com.mredrock.cyxbs.freshman.view.view.CampusView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/8/16.
 */

public class StyleOrganizationFragment extends Fragment implements CampusView{
    private List<Strategy> mList = new ArrayList<>();
    CampusStrategyPresenter strategyPresenter;
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_style_organization,container,false);

        strategyPresenter=new CampusStrategyPresenter(this,getContext());
        strategyPresenter.addData("学生组织",1,9);
        return view;
    }

    private void init()
    {
        tabLayout=(TabLayout)view.findViewById(R.id.style_organization_Tab);
        viewPager=(ViewPager)view.findViewById(R.id.style_organization_viewpager);
        for(int i=0;i<mList.size();i++)
        {
            tabLayout.addTab(tabLayout.newTab().setText(mList.get(i).getName()));
        }
        viewPager.setAdapter(new StyleOrganPagerAdapter(getChildFragmentManager(),mList));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
    @Override
    public void getData(Strategy strategy) {
        mList.add(strategy);
    }

    @Override
    public void onFinish() {
        init();
    }

}
