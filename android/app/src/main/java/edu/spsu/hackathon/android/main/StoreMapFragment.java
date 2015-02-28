package edu.spsu.hackathon.android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Point;

public class StoreMapFragment extends SupportMapFragment {

    LatLng testHomeDepotStoreLocation = new LatLng(33.9808893, -84.4350177);

    View rootView;
    GoogleMap map;

    List<Item> items;
    List<Point> path;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        MapsInitializer.initialize(getActivity());

        map = getMap();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(testHomeDepotStoreLocation, 19));

        return rootView;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    private void setupMap() {

        Map<Pair<Integer,Integer>,PolylineOptions> lines = new HashMap<>();

        for (Point point : path) {
            LatLng position = new LatLng(point.getLat(), point.getLng());

            //TODO Not finished here, but need a comment in case anyone looks at this commit ever
            //TODO But if you are looking at this commit, you da real MVP
        }
    }
}
