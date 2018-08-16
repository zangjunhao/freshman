package com.mredrock.cyxbs.freshman.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mredrock.cyxbs.freshman.model.convert.Strategy;
import com.mredrock.cyxbs.freshman.view.fragment.MilitaryMienFragment;
import com.mredrock.cyxbs.freshman.view.fragment.MilitrayHintFragment;
import com.mredrock.cyxbs.freshman.view.fragment.StyleActivityFragment;
import com.mredrock.cyxbs.freshman.view.fragment.StyleOrganizationFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 67698 on 2018/8/16.
 */

public class StylePagerAdapter extends FragmentPagerAdapter {
    private HashMap<Integer,Fragment> fragmentHashMap=new HashMap<>();
    public StylePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return creatFragment(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
    private Fragment creatFragment(int position)
    {

        Fragment fragment=fragmentHashMap.get(position);
        if(fragment==null)
        {
            switch (position)
            {
                case 0:fragment=new StyleOrganizationFragment();
                    break;
                case 1:fragment=new StyleActivityFragment();
                    break;

            }
            fragmentHashMap.put(position,fragment);
        }
        return fragment;
    }
}
