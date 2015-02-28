package edu.spsu.hackathon.android.main;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;


/**
 * This is an async task to thread out the setup of the google map
 * since that method is very expensive
 */
public class GetMapAsyncTask extends AsyncTask<MapView,Integer,GoogleMap> {

    public interface GetMapCallback {
        void onGetMapFinished(GoogleMap googleMap);
    }

    GetMapCallback callback;

    public GetMapAsyncTask(GetMapCallback callback) {
        this.callback = callback;
    }

    @Override
    protected GoogleMap doInBackground(MapView... params) {
        MapView mapView = params[0];

        return mapView.getMap();
    }

    @Override
    protected void onPostExecute(GoogleMap googleMap) {
        callback.onGetMapFinished(googleMap);
    }
}
