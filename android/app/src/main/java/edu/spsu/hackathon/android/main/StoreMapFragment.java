package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import edu.spsu.hackathon.android.R;

public class StoreMapFragment extends Fragment implements GetMapAsyncTask.GetMapCallback {

    LatLng testHomeDepotStoreLocation = new LatLng(33.9808893, -84.4350177);

    View rootView;
    GoogleMap map;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MapsInitializer.initialize(getActivity());

        rootView = inflater.inflate(R.layout.fragment_store_map, null);
        LinearLayout mapViewWrapper = (LinearLayout) rootView.findViewById(R.id.store_map_fragment_linear_layout);
        MapView mapView = (MapView) mapViewWrapper.findViewWithTag("map");
        mapView.onCreate(savedInstanceState);

        new GetMapAsyncTask(this).execute(mapView);

        return rootView;
    }

    @Override
    public void onGetMapFinished(GoogleMap googleMap) {
        map = googleMap;
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(testHomeDepotStoreLocation, 19));
    }
}
