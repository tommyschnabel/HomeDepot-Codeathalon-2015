package edu.spsu.hackathon.android.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

public class MainTabListener implements ActionBar.TabListener {
    private ViewPager viewPager;

    public MainTabListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int tabPosition = (Integer.valueOf(tab.getPosition()) == null) ? 0 : tab.getPosition();
        viewPager.setCurrentItem(tabPosition);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //Don't do anything for this
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        //Don't do anything for this
    }

}
