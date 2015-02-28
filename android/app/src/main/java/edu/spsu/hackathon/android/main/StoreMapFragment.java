package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class StoreMapFragment extends SupportMapFragment {

    LatLng testHomeDepotStoreLocation = new LatLng(33.9808893, -84.4350177);

    View rootView;
    GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        MapsInitializer.initialize(getActivity());

        map = getMap();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(testHomeDepotStoreLocation, 19));

        return rootView;
    }
}
