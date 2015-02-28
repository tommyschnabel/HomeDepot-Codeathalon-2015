package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Item;

public class ItemListFragment extends Fragment {

    View rootView;
    ListView itemList;
    List<Item> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_list, null);
        itemList = (ListView) rootView.findViewById(R.id.item_list);

        return rootView;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        setupListView();
    }

    private void setupListView() {
        //For good measure
        if (items == null) {
            return;
        }

        if (itemList == null) {
            onCreateView(LayoutInflater.from(getActivity()), null, null);
        }

        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(getActivity(), items);
        itemList.setAdapter(itemArrayAdapter);
    }
}
