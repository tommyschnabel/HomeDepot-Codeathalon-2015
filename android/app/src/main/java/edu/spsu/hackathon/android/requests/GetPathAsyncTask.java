package edu.spsu.hackathon.android.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Point;
import edu.spsu.hackathon.android.common.ServerUtils;

public class GetPathAsyncTask extends AsyncTask<Integer,Integer,List<Point>> {

    private GetPathCallback callback;

    public GetPathAsyncTask(GetPathCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Point> doInBackground(Integer... params) {
        ObjectMapper mapper = ServerUtils.getObjectMapper();
        String domain = ServerUtils.getDomain() + "/home/path";
        HttpPost post = new HttpPost(domain);

        try {
            List<ServerItemAndPointMapper.ServerPoint> path = mapper.readValue(ServerUtils.makeRequestAndReadResponse(post),
                    mapper.getTypeFactory().constructCollectionType(List.class, ServerItemAndPointMapper.ServerPoint.class));

            return ServerItemAndPointMapper.convertServerPointToPoint(path);
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Get path task failed");
            Log.e(this.getClass().toString(), e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Point> path) {
        callback.onGetPathFinished(path);
    }
}
