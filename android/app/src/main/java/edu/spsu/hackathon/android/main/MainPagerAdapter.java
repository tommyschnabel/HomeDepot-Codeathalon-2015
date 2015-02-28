package edu.spsu.hackathon.android.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Point;

public class MainPagerAdapter extends FragmentPagerAdapter {

    ItemListFragment itemListFragment;
    StoreMapFragment storeMapFragment;

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
            itemListFragment = new ItemListFragment();
            return itemListFragment;
        case 1:
            storeMapFragment = new StoreMapFragment();
            return storeMapFragment;
        default:
            throw new RuntimeException("Get count method isn't up to date with actual number of tabs");
        }
    }

    public void setItems(List<Item> items) {
        itemListFragment.setItems(items);
        storeMapFragment.setItems(items);
    }

    public void setPath(List<Point> path) {
        storeMapFragment.setPath(path);
    }
}
