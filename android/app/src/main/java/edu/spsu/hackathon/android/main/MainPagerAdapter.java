package edu.spsu.hackathon.android.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.android.gms.maps.SupportMapFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
        case 0:
            return new ItemListFragment();
        case 1:
            return new SupportMapFragment();
        default:
            throw new RuntimeException("Get count method isn't up to date with actual number of tabs");
        }
    }
}
