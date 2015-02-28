package edu.spsu.hackathon.android.requests;


import java.util.List;

import edu.spsu.hackathon.android.common.Item;

public interface GetAllItemsCallback {
    void onGetAllItemsFinished(List<Item> allItems);
}