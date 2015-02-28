package edu.spsu.hackathon.android.requests;

import java.util.List;

import edu.spsu.hackathon.android.common.Point;

public interface GetPathCallback {
    void onGetPathFinished(List<Point> path);
}
