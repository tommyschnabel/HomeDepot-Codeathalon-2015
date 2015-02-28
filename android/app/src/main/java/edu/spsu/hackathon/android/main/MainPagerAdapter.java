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
    List<Item> items;
    List<Point> path;

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

            if (items != null) {
                itemListFragment.setItems(items);
            }

            return itemListFragment;
        case 1:
            storeMapFragment = new StoreMapFragment();
            
            if (path != null) {
                storeMapFragment.setPath(path);
            }

            return storeMapFragment;
        default:
            throw new RuntimeException("Get count method isn't up to date with actual number of tabs");
        }
    }

    public void setItems(List<Item> items) {
        if (itemListFragment != null) {
            itemListFragment.setItems(items);
        }

        this.items = items;
    }

    public void setPath(List<Point> path) {
        if (storeMapFragment != null) {
            storeMapFragment.setPath(path);
        }

        this.path = path;
    }
}
