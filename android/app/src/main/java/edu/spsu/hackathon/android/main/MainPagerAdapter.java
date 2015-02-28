package edu.spsu.hackathon.android.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;

import java.util.List;

import edu.spsu.hackathon.android.R;
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
                itemListFragment.setItems(items, getOnItemClickListener());
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
            itemListFragment.setItems(items, getOnItemClickListener());
        }

        this.items = items;
    }

    public void setPath(List<Point> path) {
        if (storeMapFragment != null) {
            storeMapFragment.setPath(path);
        }

        this.path = path;
    }

    private AdapterView.OnItemClickListener getOnItemClickListener() {
        return  new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.item_checkbox);
                Boolean isToggled = checkBox.isChecked();
                checkBox.setChecked(!isToggled);
            }
        };
    }
}
