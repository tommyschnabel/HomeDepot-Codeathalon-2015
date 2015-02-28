package edu.spsu.hackathon.android.requests;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Point;

public class MockGetPathAsyncTask extends AsyncTask<Resources, Integer,List<Point>> {

    private GetPathCallback callback;

    public MockGetPathAsyncTask(GetPathCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Point> doInBackground(Resources... params) {
        Resources resources = params[0];
        InputStreamReader reader = new InputStreamReader(resources.openRawResource(R.raw.test_store_data));
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(reader, mapper.getTypeFactory().constructCollectionType(List.class, Point.class));
        } catch (IOException e) {
            Log.e(this.getClass().toString(), "Something bad happened when reading from file");

            //Rethrow error because this we want stuff to break since this is a mock
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onPostExecute(List<Point> points) {
        callback.onGetPathFinished(points);
    }
}
