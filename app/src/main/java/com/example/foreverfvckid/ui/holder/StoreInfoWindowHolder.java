package com.example.foreverfvckid.ui.holder;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.StoreLocation;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

public class StoreInfoWindowHolder extends InfoWindow {

    public StoreInfoWindowHolder(int layoutResId, MapView mapView) {
        super(layoutResId, mapView);
    }

    @Override
    public void onOpen(Object item) {
        TextView tvLocationName = mView.findViewById(R.id.tvNamaLokasi);
        TextView tvLocationAddress = mView.findViewById(R.id.tvAlamat);
        ImageView btnClose = mView.findViewById(R.id.imageClose);

        if (item instanceof Marker) {
            Marker marker = (Marker) item;
            btnClose.setOnClickListener(v -> marker.closeInfoWindow());
            Object data = marker.getRelatedObject();
            if (data instanceof StoreLocation) {
                StoreLocation location = (StoreLocation) data;
                tvLocationName.setText(location.getStoreName());
                tvLocationAddress.setText(location.getStoreAddress());
            }
        }
    }

    @Override
    public void onClose() {}
}
