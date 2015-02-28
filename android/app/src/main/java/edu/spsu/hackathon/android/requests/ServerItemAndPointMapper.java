package edu.spsu.hackathon.android.requests;

import java.util.ArrayList;
import java.util.List;

import edu.spsu.hackathon.android.common.Item;
import edu.spsu.hackathon.android.common.Point;
import edu.spsu.hackathon.android.common.Type;

public class ServerItemAndPointMapper {

    public static List<Point> convertServerPointToPoint(List<ServerPoint> serverPoints) {
        if (serverPoints == null) {
            return null;
        }

        List<Point> points = new ArrayList<>();

        for (ServerPoint serverPoint : serverPoints) {
            Point point = new Point();
            point.setType(Type.valueOf(serverPoint.getTypeName()));
            point.setLng(serverPoint.getLng());
            point.setLat(serverPoint.getLat());

            points.add(point);
        }

        return points;
    }

    public static List<Item> convertServerItemToItem(List<ServerItem> serverItems) {
        if (serverItems == null) {
            return null;
        }

        List<Item> items = new ArrayList<>();

        for (ServerItem serverItem : serverItems) {
            Item item = new Item();
            item.setName(serverItem.getItemName());
            item.setType(serverItem.getTypeName());
            item.setId(serverItem.getItemID());

            items.add(item);
        }

        return items;
    }

    public class ServerPoint {

        private Integer TypeID;
        private String TypeName;
        private Double Lat;
        private Double Lng;
        private List<Integer> ID;

        public Integer getTypeID() {
            return TypeID;
        }

        public void setTypeID(Integer typeID) {
            TypeID = typeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String typeName) {
            TypeName = typeName;
        }

        public Double getLat() {
            return Lat;
        }

        public void setLat(Double lat) {
            Lat = lat;
        }

        public Double getLng() {
            return Lng;
        }

        public void setLng(Double lng) {
            Lng = lng;
        }

        public List<Integer> getID() {
            return ID;
        }

        public void setID(List<Integer> ID) {
            this.ID = ID;
        }
    }

    public class ServerItem {

        private Integer itemID;
        private String itemName;
        private Type typeName;

        public Integer getItemID() {
            return itemID;
        }

        public void setItemID(Integer itemID) {
            this.itemID = itemID;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Type getTypeName() {
            return typeName;
        }

        public void setTypeName(Type typeName) {
            this.typeName = typeName;
        }
    }
}
