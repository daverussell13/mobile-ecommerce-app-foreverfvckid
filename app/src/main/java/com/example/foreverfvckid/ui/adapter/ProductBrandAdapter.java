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
import com.example.foreverfvckid.data.model.ProductBrand;

import java.util.ArrayList;

public class ProductBrandAdapter extends RecyclerView.Adapter<ProductBrandAdapter.ProductBrandViewHolder> {

    private final ArrayList<ProductBrand> productBrands;
    private final Context context;

    public ProductBrandAdapter(Context context, ArrayList<ProductBrand> productBrands) {
        this.context = context;
        this.productBrands = productBrands;
    }

    @NonNull
    @Override
    public ProductBrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_product_brands, parent, false);
        return new ProductBrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductBrandViewHolder holder, int position) {
        ProductBrand productBrand = productBrands.get(position);
        holder.brandImage.setImageResource(productBrand.resolveImageId());
        holder.brandTitle.setText(productBrand.getName());
    }

    @Override
    public int getItemCount() {
        return productBrands.size();
    }

    public static class ProductBrandViewHolder extends RecyclerView.ViewHolder {

        private final ImageView brandImage;
        private final TextView brandTitle;

        public ProductBrandViewHolder(@NonNull View itemView) {
            super(itemView);
            brandImage = itemView.findViewById(R.id.tv_brand_image);
            brandTitle = itemView.findViewById(R.id.tv_brand_title);
        }
    }
}
