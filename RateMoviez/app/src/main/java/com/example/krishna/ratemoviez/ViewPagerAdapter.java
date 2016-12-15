package com.example.krishna.ratemoviez;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by krishna on 19/9/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private Fragment fragment;


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                fragment = new CurrentFragment();
                break;
            case 1:
                fragment = new RatingFragment();
                break;
            case 2:
                fragment = new FavouriteFragment();
                break;
        }


    return fragment;
    }

    @Override
    public int getCount() {
        return 3;           // As there are only 3 Tabs
    }
}

