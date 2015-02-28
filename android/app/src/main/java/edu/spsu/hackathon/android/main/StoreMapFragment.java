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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import edu.spsu.hackathon.android.R;
import edu.spsu.hackathon.android.common.Point;
import edu.spsu.hackathon.android.common.Type;

public class StoreMapFragment extends SupportMapFragment {

    LatLng testHomeDepotStoreLocation = new LatLng(33.9808893, -84.4350177);

    View rootView;
    GoogleMap map;

    List<Point> path;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        MapsInitializer.initialize(getActivity());

        map = getMap();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(testHomeDepotStoreLocation, 19));

        if (path != null) {
            setupMap();
        }

        return rootView;
    }

    public void setPath(List<Point> path) {
        this.path = path;
        setupMap();
    }

    private void setupMap() {
        if (map == null) {
            return;
        }

        map.clear();

        //Fuck regular for loops, but sometimes you just gotta use em
        for (int i = 0; i < path.size(); ++i) {

            //Make sure we aren't at the last element, no line needed then
            if (i + 1 >= path.size()) {
                return;
            }

            //Draw line from current point to the next point
            Point currentPoint = path.get(i);
            Point nextPoint = path.get(i + 1);

            LatLng currentPosition = new LatLng(currentPoint.getLat(), currentPoint.getLng());
            LatLng nextPosition = new LatLng(nextPoint.getLat(), nextPoint.getLng());

            PolylineOptions line = new PolylineOptions();
            line.add(currentPosition, nextPosition);
            line.width(5);
            line.color(getResources().getColor(R.color.home_depot_grey));

            map.addPolyline(line);

            //If the point is a place the user will stop, add a marker to let them know
            if (currentPoint.getType().equals(Type.none)
                    || currentPoint.getType().equals(Type.enter)
                    || currentPoint.getType().equals(Type.checkout)) {
                continue;
            }

            MarkerOptions marker = new MarkerOptions();
            marker.position(currentPosition);
            marker.title(currentPoint.getType().toString());

            map.addMarker(marker);
        }
    }
}
