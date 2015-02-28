package edu.spsu.hackathon.android.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.requests.AddItemsAsyncTask;
import edu.spsu.hackathon.android.requests.GetAllItemsAsyncTask;
import edu.spsu.hackathon.android.requests.GetAllItemsCallback;

public class AddItemsActivity extends ActionBarActivity implements GetAllItemsCallback, AddItemsAsyncTask.AddItemsCallback {

    public static String ITEMS = "items";
    public static Integer ACTION_ADDED_ITEMS = 1;
    public static Integer ACTION_NOTHING_HAPPENED = 2;

    ItemListFragment itemListFragment;
    List<Item> currentlyAddedItems;
    List<Item> items;
    Set<Integer> checkedItems = new HashSet<>();
    List<Item> newItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetAllItemsAsyncTask(this).execute();

        setContentView(R.layout.activity_add_items);
        itemListFragment = (ItemListFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.add_items_fragment_tag));

        String currentItemsString = getIntent().getStringExtra(ITEMS);

        if (currentItemsString == null) {
            currentlyAddedItems = new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            currentlyAddedItems = mapper.readValue(currentItemsString, mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Couldn't deserialize currently added items");
            Log.e(this.getClass().toString(), e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
        case R.id.action_confirm_add_items:
            newItems = new ArrayList<>();
            for (Item obj : items) {
                if (checkedItems.contains(obj.getId())) {
                    newItems.add(obj);
                }
            }

            new AddItemsAsyncTask(this).execute((Integer[])checkedItems.toArray());

            case R.id.action_cancel_add_items:
            setResult(ACTION_NOTHING_HAPPENED);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetAllItemsFinished(List<Item> allItems) {

        if (currentlyAddedItems == null) {
            return;
        }

        Map<Integer,Item> allItemMap = new HashMap<>();
        for (Item item : allItems) {
            allItemMap.put(item.getId(), item);
        }

        //TODO Get rid of this ugly O(n^2) code
        for (Item item : allItems) {
            for (Item selectedItem : currentlyAddedItems) {
                if (item.getId().equals(selectedItem.getId())) {
                    allItemMap.remove(item.getId());
                }
            }
        }

        items = new ArrayList<>();
        for (Map.Entry<Integer,Item> entry : allItemMap.entrySet()) {
            items.add(entry.getValue());
        }

        setItemList();
    }

    private void setItemList() {
        if (items == null) {
            return;
        } else if (itemListFragment == null) {
            itemListFragment = new ItemListFragment();
        }

        itemListFragment.setItems(items, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.item_checkbox);
                Boolean isChecked = checkBox.isChecked();
                checkBox.setChecked(!isChecked);

                if (checkedItems.contains(items.get(position).getId())) {
                    checkedItems.remove(items.get(position).getId());
                } else if (!checkedItems.contains(items.get(position).getId())) {
                    checkedItems.add(items.get(position).getId());
                }
            }
        }, new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //Don't want to do anything on long click here
                return true;
            }
        });
    }

    @Override
    public void onAddItemsFinished(Boolean success) {
        for (Item i : newItems) {
            currentlyAddedItems.add(i);
        }

        String newItemsString = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            newItemsString = mapper.writeValueAsString(currentlyAddedItems);
        } catch (JsonProcessingException e) {
            Log.e(this.getClass().toString(), "Couldn't serialize new items");
            Log.e(this.getClass().toString(), e.getMessage());
            Toast.makeText(this, getString(R.string.add_failed), Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent();
        intent.putExtra(ITEMS, newItemsString);
        setResult(ACTION_ADDED_ITEMS, intent);

        finish();
    }
}
