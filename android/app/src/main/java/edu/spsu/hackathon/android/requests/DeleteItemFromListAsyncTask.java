package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;

import edu.spsu.hackathon.android.common.ServerUtils;

public class DeleteItemFromListAsyncTask extends AsyncTask<Integer,Integer,Boolean> {

    public interface DeleteItemCallback {
        void onDeleteItemFinished(Boolean success);
    }

    private DeleteItemCallback callback;

    @Override
    protected Boolean doInBackground(Integer... params) {
        Integer id = params[0];
        String domain = ServerUtils.getDomain() + "/home/delete?ID=" + id;
        HttpPost post = new HttpPost(domain);

        try {
            ServerUtils.makeRequestAndVerifyNoResponse(post);
            return true;
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Failed to delete item");
            Log.e(this.getClass().toString(), e.getMessage());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        callback.onDeleteItemFinished(success);
    }

}
