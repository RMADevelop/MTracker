package com.example.roma.mtracker_v3.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.roma.mtracker_v3.vieww.FragmentInsertDescription;
import com.example.roma.mtracker_v3.vieww.FragmentMonthThis;

/**
 * Created by Roma on 27.06.2017.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private String[] tabs;

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[] {
                "first", "MontThis", "third"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentInsertDescription();
            case 1:
                return  new FragmentMonthThis();
            case 2:
                return new FragmentInsertDescription();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
