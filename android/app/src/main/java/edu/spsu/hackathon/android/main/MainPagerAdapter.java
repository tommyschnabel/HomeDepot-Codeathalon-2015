package edu.spsu.hackathon.android.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Point;
import edu.spsu.hackathon.android.requests.DeleteItemFromListAsyncTask;

public class MainPagerAdapter extends FragmentPagerAdapter implements DeleteItemFromListAsyncTask.DeleteItemCallback {

    private ItemListFragment itemListFragment;
    private StoreMapFragment storeMapFragment;
    private List<Item> items;
    private List<Point> path;
    private Queue<Item> deleteQueue = new PriorityQueue<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * returns the number of fragments
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Gets each fragment for the corresponding actionbar tab
     * @param position tab position
     * @return fragment for tab
     * @throws edu.spsu.hackathon.android.main.MainPagerAdapter.MainPagerAdapterException if a
     *         fragment greater than the fragment count is requested
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
        case 0:
            itemListFragment = new ItemListFragment();

            if (items != null) {
                itemListFragment.setItems(items, getOnItemClickListener(), getOnLongClickListener());
            }

            return itemListFragment;
        case 1:
            storeMapFragment = new StoreMapFragment();

            if (path != null) {
                storeMapFragment.setPath(path);
            }

            return storeMapFragment;
        default:
            throw new MainPagerAdapterException("Get count method isn't up to date with actual number of tabs");
        }
    }

    public void setItems(List<Item> items) {
        if (itemListFragment != null) {
            itemListFragment.setItems(items, getOnItemClickListener(), getOnLongClickListener());
        }

        this.items = items;
    }

    public void setPath(List<Point> path) {
        if (storeMapFragment != null) {
            storeMapFragment.setPath(path);
        }

        this.path = path;
    }

    private AdapterView.OnItemLongClickListener getOnLongClickListener() {
        return new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.item_checkbox);

                if (!checkBox.isChecked()) {
                    Item itemToDelete = null;
                    for (Item item : items) {
                        if (item.getId().equals(items.get(position).getId())) {
                            itemToDelete = item;
                            break;
                        }
                    }
                    if (itemToDelete != null) {
                        deleteQueue.add(itemToDelete);
                        //MOCK
                        //onDeleteItemFinished(true);
                        new DeleteItemFromListAsyncTask(MainPagerAdapter.this).execute(itemToDelete.getId());
                    }

                    return true;
                }
                return false;
            }
        };

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

    @Override
    public void onDeleteItemFinished(Boolean success) {
        items.remove(deleteQueue.remove());
        itemListFragment.refreshListView(items);
    }

    public class MainPagerAdapterException extends RuntimeException {

        public MainPagerAdapterException(String detailMessage) {
            super(detailMessage);
        }

        public MainPagerAdapterException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }
    }
}
