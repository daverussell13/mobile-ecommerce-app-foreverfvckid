package com.example.foreverfvckid.data.model;

import com.example.foreverfvckid.R;

import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreLocation {

    private final String storeName;
    private final String storeAddress;
    private final Double lat;
    private final Double lon;
    private Marker mapMarker;

    private static final StoreLocation[] storeLocations =  {
            new StoreLocation(
                    "Outlet A",
                    "Bandung, Ciroyom, Kec. Andir, Kota Bandung, Jawa Barat 40182",
                    -6.915845285206341,
                    107.58613438261567
            ),
            new StoreLocation(
                    "Outlet B",
                    "Jl. Kebon Jati No.218, Kb. Jeruk, Kec. Andir, Kota Bandung, Jawa Barat 40181",
                    -6.916319633556482,
                    107.59370478791487
            ),
            new StoreLocation(
                    "Outlet C",
                    "Jl. Simpang Industri 3-5, Arjuna, Kec. Cicendo, Kota Bandung, Jawa Barat 40172",
                    -6.912804868628957,
                    107.59174141113208
            )
    };

    public StoreLocation(String storeName, String storeAddress, Double lat, Double lon) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.lat = lat;
        this.lon = lon;
    }

    public static ArrayList<StoreLocation> getStoreLocations() {
        return new ArrayList<>(Arrays.asList(storeLocations));
    }

    public static BoundingBox getStoreBoundingBox() {
        double north = -90;
        double south = 90;
        double west = 180;
        double east = -180;
        for (StoreLocation location : storeLocations) {
            north = Math.max(location.getLat(), north);
            south = Math.min(location.getLat(), south);

            west = Math.min(location.getLon(), west);
            east = Math.max(location.getLon(), east);
        }
        return new BoundingBox(north, west, south, east);
    }

    public GeoPoint getGeoPoint() {
        return new GeoPoint(lat, lon);
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public Integer resolveImageId() {
        switch (storeName) {
            case "Outlet A":
                return R.drawable.store_outlet_a;
            case "Outlet B":
                return R.drawable.store_outlet_b;
            case "Outlet C":
                return R.drawable.store_outlet_c;
        }
        return -1;
    }

    public void setMapMarker(Marker mapMarker) {
        this.mapMarker = mapMarker;
    }

    public Marker getMapMarker() {
        return mapMarker;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
