package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.ServerUtils;

public class GetItemsAsyncTask extends AsyncTask<Integer,Integer,List<Item>> {

    GetItemsCallback callback;

    public GetItemsAsyncTask(GetItemsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Item> doInBackground(Integer... params) {
        ObjectMapper mapper = ServerUtils.getObjectMapper();
        String domain = ServerUtils.getDomain() + "/home/current";
        HttpPost post = new HttpPost(domain);

        try {
            List<ServerItemAndPointMapper.ServerItem> serverItems = mapper.readValue(ServerUtils.makeRequestAndReadResponse(post),
                    mapper.getTypeFactory().constructCollectionType(List.class, ServerItemAndPointMapper.ServerItem.class));

            return ServerItemAndPointMapper.convertServerItemToItem(serverItems);
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Get items task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Item> items) {
        callback.onGetItemsFinished(items);
    }
}
