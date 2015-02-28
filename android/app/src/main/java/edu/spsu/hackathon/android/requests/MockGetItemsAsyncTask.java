package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Type;

public class MockGetItemsAsyncTask extends AsyncTask<Integer,Integer,List<Item>> {

    private GetItemsCallback callback;

    public MockGetItemsAsyncTask(GetItemsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Item> doInBackground(Integer... params) {
        List<Item> items = new ArrayList<>();
        Item item = new Item();

        item.setId(1);
        item.setName("Hammer");
        item.setType(Type.tools);
        items.add(item);

        item = new Item();
        item.setId(2);
        item.setName("Nails");
        item.setType(Type.tools);
        items.add(item);

        item = new Item();
        item.setId(3);
        item.setName("Paint");
        item.setType(Type.paint);
        items.add(item);

        item = new Item();
        item.setId(23);
        item.setName("Paint Roller");
        item.setType(Type.paint);
        items.add(item);

        item = new Item();
        item.setId(13);
        item.setName("Window Pane");
        item.setType(Type.doors);
        items.add(item);

        return items;
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        callback.onGetItemsFinished(items);
    }
}
