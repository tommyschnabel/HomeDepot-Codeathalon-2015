package edu.spsu.hackathon.android.common;

import java.util.List;

public class Point {

    Integer id;
    Double lat;
    Double lng;
    Type type;
//    List<Integer> itemIds;
    List<Integer> connectedTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

//    public List<Integer> getItemIds() {
//        return itemIds;
//    }
//
//    public void setItemIds(List<Integer> itemIds) {
//        this.itemIds = itemIds;
//    }

    public List<Integer> getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(List<Integer> connectedTo) {
        this.connectedTo = connectedTo;
    }
}
