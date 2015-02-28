package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.ServerUtils;

public class AddItemsAsyncTask extends AsyncTask<List<Integer>,Integer,List<Item>> {

    public interface AddItemsCallback {
        void onAddItemsFinished(List<Item> items);
    }

    AddItemsCallback callback;

    public AddItemsAsyncTask(AddItemsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Item> doInBackground(List<Integer>... params) {
        List<Integer> itemsToAdd = params[0];

        ObjectMapper mapper = ServerUtils.getObjectMapper();
        String domain = ServerUtils.getDomain() + "/home/add";
        HttpPost post = new HttpPost(domain);

        try {
            StringEntity entity = new StringEntity(mapper.writeValueAsString(itemsToAdd));
            post.setEntity(entity);
        } catch (UnsupportedEncodingException|JsonProcessingException e) {
            Log.e(this.getClass().toString(), "Add items task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }

        try {
            return mapper.readValue(ServerUtils.makeRequestAndReadResponse(post), mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Get items task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        callback.onAddItemsFinished(items);
    }
}
