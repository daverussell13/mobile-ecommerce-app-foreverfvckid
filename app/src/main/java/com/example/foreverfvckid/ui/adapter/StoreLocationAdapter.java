package com.example.foreverfvckid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.StoreLocation;

import java.util.ArrayList;

public class StoreLocationAdapter extends RecyclerView.Adapter<StoreLocationAdapter.StoreLocationViewHolder> {

    private final ArrayList<StoreLocation> storeLocations;
    private final Context context;
    private final OnClickListener onClickCallback;

    public StoreLocationAdapter(Context context, ArrayList<StoreLocation> storeLocations, OnClickListener onClickCallback) {
        this.context = context;
        this.storeLocations = storeLocations;
        this.onClickCallback = onClickCallback;
    }

    @NonNull
    @Override
    public StoreLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_store_location, parent, false);
        return new StoreLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreLocationViewHolder holder, int position) {
        StoreLocation store = storeLocations.get(position);
        holder.storeImagePreview.setImageResource(store.resolveImageId());
        holder.storeName.setText(store.getStoreName());
    }

    @Override
    public int getItemCount() {
        return storeLocations.size();
    }

    public class StoreLocationViewHolder extends RecyclerView.ViewHolder {

        private final ImageView storeImagePreview;
        private final TextView storeName;

        public StoreLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            storeImagePreview = itemView.findViewById(R.id.storeImagePreview);
            storeName = itemView.findViewById(R.id.storeName);
            itemView.setOnClickListener(v -> onClickCallback.onCLick(v, storeLocations.get(getAdapterPosition())));
        }
    }

    public interface OnClickListener {
        void onCLick(View view, StoreLocation storeLocation);
    }
}
