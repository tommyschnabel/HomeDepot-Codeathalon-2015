package edu.spsu.hackathon.android.main;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.tools.LocationViewerToolActivity;

public class MainActivity extends ActionBarActivity {

    ActionBar actionBar;
    MainPagerAdapter pagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPage();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //If the device has cleared everything in memory, reload it all so the app doesn't crash
        if (actionBar == null || pagerAdapter == null || viewPager == null) {
            setupPage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch(item.getItemId()) {
        case R.id.action_settings:
            Toast.makeText(this, R.string.alert_no_settings, Toast.LENGTH_LONG).show();
            return true;
        case R.id.action_location_tool:
            intent = new Intent(MainActivity.this, LocationViewerToolActivity.class);
            startActivity(intent);
            return true;        }

        return super.onOptionsItemSelected(item);
    }

    private void setupPage() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        MainTabListener tabListener = new MainTabListener(viewPager);
        actionBar.removeAllTabs();
        actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_action_view_as_list).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_action_directions).setTabListener(tabListener));
    }
}
