package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Item;

public class ItemListFragment extends Fragment {

    View rootView;
    ListView itemList;
    List<Item> items;
    ProgressBar loadingWheel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_list, null);
        itemList = (ListView) rootView.findViewById(R.id.item_list);

        //TODO Figure out why this is never put on the root view even though its defined in the layout
//        loadingWheel = (ProgressBar) rootView.findViewById(R.id.item_list_loading_spinner);
//        loadingWheel.setVisibility(View.VISIBLE);

        return rootView;
    }

    public void setItems(List<Item> items, AdapterView.OnItemClickListener onItemClickListener) {
        this.items = items;
        setupListView(onItemClickListener);
    }

    private void setupListView(AdapterView.OnItemClickListener onItemClickListener) {
        //For good measure
        if (items == null || itemList == null) {
            return;
        }

        //TODO Once the loading wheel is fixed above, uncomment here
//        loadingWheel.setVisibility(View.INVISIBLE);
        itemList.setVisibility(View.VISIBLE);
        itemList.setOnItemClickListener(onItemClickListener);

        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(getActivity(), items);
        itemList.setAdapter(itemArrayAdapter);
    }
}
