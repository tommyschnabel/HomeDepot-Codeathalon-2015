package edu.spsu.hackathon.android.tools;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.spsu.hackathon.android.R;


public class LocationViewerToolActivity extends ActionBarActivity implements GoogleMap.OnCameraChangeListener {

    GoogleMap map;
    MarkerOptions centerMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_viewer);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("map");
        map = ((SupportMapFragment) fragment).getMap();
        map.setOnCameraChangeListener(this);

        LatLng testHomeDepotStoreLocation = new LatLng(33.9808893, -84.4350177);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(testHomeDepotStoreLocation, 19));

        centerMarker = new MarkerOptions().position(testHomeDepotStoreLocation).title("Current Position");
        map.addMarker(centerMarker);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        TextView lat = (TextView) findViewById(R.id.lat);
        TextView lng = (TextView) findViewById(R.id.lng);

        lat.setText(String.valueOf(cameraPosition.target.latitude) + ", ");
        lng.setText(String.valueOf(cameraPosition.target.longitude));

        map.clear();
        centerMarker.position(cameraPosition.target);
        map.addMarker(centerMarker);
    }
}
