package com.mredrock.cyxbs.freshman.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mredrock.cyxbs.freshman.view.fragment.MilitaryMienFragment;
import com.mredrock.cyxbs.freshman.view.fragment.MilitrayHintFragment;

import java.util.HashMap;

/**
 * Created by 67698 on 2018/8/10.
 */

public class MilitaryViewPagerAdapter extends FragmentPagerAdapter {
    private HashMap<Integer,Fragment> fragmentHashMap=new HashMap<>();
    public MilitaryViewPagerAdapter(FragmentManager fm) {
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
                case 0:fragment=new MilitaryMienFragment();
                    break;
                case 1:fragment=new MilitrayHintFragment();
                    break;

            }
            fragmentHashMap.put(position,fragment);
        }
        return fragment;
    }
}
