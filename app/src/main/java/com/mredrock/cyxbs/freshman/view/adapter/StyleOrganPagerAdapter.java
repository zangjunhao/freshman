package com.mredrock.cyxbs.freshman.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.view.fragment.StyleActivityFragment;
import com.mredrock.cyxbs.freshman.view.fragment.StyleOrganizationFragment;
import com.mredrock.cyxbs.freshman.view.fragment.StyleOrganization_fragment_fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 67698 on 2018/8/16.
 */

public class StyleOrganPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments=new ArrayList<>();
    List<Strategy> lists=new ArrayList<>();
    public StyleOrganPagerAdapter(FragmentManager fm,List<Strategy> lists) {
        super(fm);
        for(int i=0;i<lists.size();i++)
        {
            fragments.add(new StyleOrganization_fragment_fragment(lists.get(i).getName(),lists.get(i).getContent(),lists.get(i).getPicture().get(0)));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



}
