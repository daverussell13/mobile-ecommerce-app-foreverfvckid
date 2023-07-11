package com.example.foreverfvckid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.foreverfvckid.BuildConfig;
import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.StoreLocation;
import com.example.foreverfvckid.ui.adapter.StoreLocationAdapter;
import com.example.foreverfvckid.ui.holder.StoreInfoWindowHolder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;

public class StoreLocationActivity extends AppCompatActivity {

    private FrameLayout bottomSheetLayout;
    private MapView mapView;
    private ArrayList<StoreLocation> storeLocations;
    private RecyclerView ourStoreRv;
    private FloatingActionButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context ctx = getApplicationContext();
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        storeLocations = StoreLocation.getStoreLocations();
        setContentView(R.layout.activity_store_location);
        initUI();
        configUI();
        initMarker();
        initHandlers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StoreLocationAdapter storeLocationAdapter = new StoreLocationAdapter(
                this,
                storeLocations,
                (view, storeLocation) -> {
                    clearInfoWindows();
                    mapView.getController().animateTo(storeLocation.getGeoPoint());
                    storeLocation.getMapMarker().showInfoWindow();
                }
        );
        ourStoreRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ourStoreRv.setAdapter(storeLocationAdapter);
    }

    private void initUI() {
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        mapView = findViewById(R.id.map);
        ourStoreRv = findViewById(R.id.ourStoreRv);
        backBtn = findViewById(R.id.backButton);
    }

    private void configUI() {
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setMultiTouchControls(true);
        mapView.getController().setCenter(storeLocations.get(0).getGeoPoint());
        mapView.getController().setZoom(18.0);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);

        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(120);
        bottomSheetBehavior.setHideable(false);
    }

    private void initMarker() {
        for (StoreLocation storeLocation : storeLocations) {
            Marker marker = new Marker(mapView);
            StoreInfoWindowHolder storeInfoWindowHolder = new StoreInfoWindowHolder(R.layout.custom_tooltip_store_location, mapView);
            marker.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_place_big));
            marker.setTitle(storeLocation.getStoreName() + ", " + storeLocation.getStoreAddress());
            marker.setPosition(storeLocation.getGeoPoint());
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setInfoWindow(storeInfoWindowHolder);
            marker.setRelatedObject(storeLocation);
            marker.setOnMarkerClickListener((mark, mapView) -> {
                clearInfoWindows();
                if (mark.getRelatedObject() instanceof StoreLocation) {
                    StoreLocation location = (StoreLocation) mark.getRelatedObject();
                    mapView.getController().animateTo(location.getGeoPoint());
                    mark.showInfoWindow();
                }
                return true;
            });
            storeLocation.setMapMarker(marker);
            mapView.getOverlays().add(marker);
            mapView.invalidate();
        }
    }

    private void clearInfoWindows() {
        for (StoreLocation location : storeLocations) {
            location.getMapMarker().closeInfoWindow();
        }
    }

    private void initHandlers() {
        backBtn.setOnClickListener(v -> onBackPressed());
    }
}