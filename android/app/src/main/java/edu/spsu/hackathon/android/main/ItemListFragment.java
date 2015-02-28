package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.spsu.hackathon.android.R;

public class ItemListFragment extends Fragment {

    View rootView;
    ListView itemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_list, null);
        itemList = (ListView) rootView.findViewById(R.id.item_list);

        return rootView;
    }
}
