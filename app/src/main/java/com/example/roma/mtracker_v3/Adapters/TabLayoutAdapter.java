package com.example.roma.mtracker_v3.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.roma.mtracker_v3.vieww.Fragment.Page_Converter;
import com.example.roma.mtracker_v3.vieww.Fragment.Page_Month_This;
import com.example.roma.mtracker_v3.vieww.Fragment.Page_Transactions;

/**
 * Created by Roma on 27.06.2017.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private String[] tabs;

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "Transaction", "MothThis", "Converter"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Page_Transactions();
            case 1:
                return new Page_Month_This();
            case 2:
                return new Page_Converter();
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
