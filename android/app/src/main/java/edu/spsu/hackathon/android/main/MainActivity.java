package edu.spsu.hackathon.android.main;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Point;
import edu.spsu.hackathon.android.requests.GetItemsAsyncTask;
import edu.spsu.hackathon.android.requests.GetItemsCallback;
import edu.spsu.hackathon.android.requests.GetPathAsyncTask;
import edu.spsu.hackathon.android.requests.GetPathCallback;
import edu.spsu.hackathon.android.tools.LocationViewerToolActivity;

public class MainActivity extends ActionBarActivity implements GetPathCallback,
                                                               GetItemsCallback {

    ActionBar actionBar;
    MainPagerAdapter pagerAdapter;
    ViewPager viewPager;

    List<Item> items;
    List<Point> path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPage();
        requestInfo();
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
            return true;
        case R.id.action_add_items:
            intent = new Intent(MainActivity.this, AddItemsActivity.class);
            ObjectMapper mapper = new ObjectMapper();


            //Pass the current items to add activity so we can filter them out of the potentials
            String itemsString ="";
            if (items != null && !items.isEmpty()) {

                try {
                    itemsString = mapper.writeValueAsString(items);
                } catch (JsonProcessingException e) {
                    Log.e(this.getClass().toString(), "Couldn't serialize items string");
                    Log.e(this.getClass().toString(), e.getMessage());
                    return false;
                }
                intent.putExtra(AddItemsActivity.ITEMS, itemsString);
            }

            startActivityForResult(intent, AddItemsActivity.ACTION_ADDED_ITEMS);
            return true;
        case R.id.action_refresh:
            setupPage();
            requestInfo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AddItemsActivity.ACTION_ADDED_ITEMS) {

            List<Item> newItemsList = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            try {
                newItemsList = mapper.readValue(data.getStringExtra(AddItemsActivity.ITEMS), mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
            } catch (IOException e) {
                Log.e(this.getClass().toString(), "Couldn't deserialize currently added items");
                Log.e(this.getClass().toString(), e.getMessage());
            }

            onGetItemsFinished(newItemsList);
        }
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

    private void requestInfo() {
        new GetItemsAsyncTask(this).execute(0); //random value since you can't have a Void generic
        new GetPathAsyncTask(this).execute(0); //random value since you can't have a Void generic
    }

    @Override
    public void onGetItemsFinished(List<Item> items) {
        this.items = items;
        pagerAdapter.setItems(items);
    }

    @Override
    public void onGetPathFinished(List<Point> path) {
        pagerAdapter.setPath(path);
    }

}
