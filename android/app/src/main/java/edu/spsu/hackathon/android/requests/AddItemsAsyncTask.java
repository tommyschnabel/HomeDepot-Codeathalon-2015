package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import edu.spsu.hackathon.android.common.ServerUtils;

public class AddItemsAsyncTask extends AsyncTask<Integer[],Integer,Boolean> {

    public interface AddItemsCallback {
        void onAddItemsFinished(Boolean success);
    }

    AddItemsCallback callback;

    public AddItemsAsyncTask(AddItemsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Integer[]... params) {
        Integer[] itemsToAdd = params[0];

        ObjectMapper mapper = ServerUtils.getObjectMapper();
        String domain = ServerUtils.getDomain() + "/home/add";
        HttpPost post = new HttpPost(domain);

        try {
            StringEntity entity = new StringEntity(mapper.writeValueAsString(itemsToAdd));
            post.setEntity(entity);
        } catch (UnsupportedEncodingException|JsonProcessingException e) {
            Log.e(this.getClass().toString(), "Add items task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return false;
        }

        try {
            ServerUtils.makeRequestAndVerifyNoResponse(post);
            return true;
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Get items task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        callback.onAddItemsFinished(success);
    }
}
