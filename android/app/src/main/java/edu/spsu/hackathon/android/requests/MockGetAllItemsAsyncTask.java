package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Type;

public class MockGetAllItemsAsyncTask extends AsyncTask<Integer,Integer,List<Item>> {

    private GetAllItemsCallback callback;

    public MockGetAllItemsAsyncTask(GetAllItemsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Item> doInBackground(Integer... params) {
        List<Item> items = new ArrayList<>();
        Item item = new Item();

        item.setId(40);
        item.setName("Screwdriver");
        item.setType(Type.tools);
        items.add(item);

        item = new Item();
        item.setId(2);
        item.setName("Nails");
        item.setType(Type.tools);
        items.add(item);

        return items;
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        callback.onGetAllItemsFinished(items);
    }
}
