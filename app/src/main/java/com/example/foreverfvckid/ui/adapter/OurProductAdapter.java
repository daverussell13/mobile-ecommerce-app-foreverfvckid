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
import com.example.foreverfvckid.data.model.Product;

import java.util.ArrayList;

public class OurProductAdapter extends RecyclerView.Adapter<OurProductAdapter.OurProductHolder> {

    private final ArrayList<Product> products;
    private final Context context;

    public OurProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public OurProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_new_arrival, parent, false);
        return new OurProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurProductHolder holder, int position) {
        Product product = products.get(position);
        holder.productImage.setImageResource(product.resolveImageId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getFormattedPrice());
        holder.productDesc.setText(product.getDesc());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class OurProductHolder extends RecyclerView.ViewHolder {

        private final ImageView productImage;
        private final TextView productName;
        private final TextView productDesc;
        private final TextView productPrice;

        public OurProductHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDesc = itemView.findViewById(R.id.productDesc);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
