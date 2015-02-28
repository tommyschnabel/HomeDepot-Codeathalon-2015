package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.ServerUtils;

public class GetAllItemsAsyncTask extends AsyncTask<Integer,Integer,List<Item>> {

    private GetAllItemsCallback callback;

    public GetAllItemsAsyncTask(GetAllItemsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Item> doInBackground(Integer... params) {
        ObjectMapper mapper = ServerUtils.getObjectMapper();
        String domain = ServerUtils.getDomain() + "/home/request";
        HttpPost post = new HttpPost(domain);

        try {
            return mapper.readValue(ServerUtils.makeRequestAndReadResponse(post), mapper.getTypeFactory().constructCollectionType(List.class, Item.class));
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Get all items task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        callback.onGetAllItemsFinished(items);
    }
}
