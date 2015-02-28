package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.item_checkbox);
                Boolean isToggled = checkBox.isChecked();
                checkBox.setChecked(!isToggled);
            }
        });

        //TODO Figure out why this is never put on the root view even though its defined in the layout
//        loadingWheel = (ProgressBar) rootView.findViewById(R.id.item_list_loading_spinner);
//        loadingWheel.setVisibility(View.VISIBLE);

        if (items != null) {
            setupListView();
        }

        return rootView;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        setupListView();
    }

    private void setupListView() {
        //For good measure
        if (items == null || itemList == null) {
            return;
        }

        //TODO Once the loading wheel is fixed above, uncomment here
//        loadingWheel.setVisibility(View.INVISIBLE);
        itemList.setVisibility(View.VISIBLE);

        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(getActivity(), items);
        itemList.setAdapter(itemArrayAdapter);
    }
}
