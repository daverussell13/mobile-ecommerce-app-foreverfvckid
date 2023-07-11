package com.example.foreverfvckid.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foreverfvckid.R;

public class PurchasedProductViewHolder {

    private final View view;
    private final TextView productName;
    private final TextView productQuantity;
    private final ImageView productImage;

    public PurchasedProductViewHolder(Context context, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.view_holder_purchased_product, parent, false);
        productName = view.findViewById(R.id.productName);
        productQuantity = view.findViewById(R.id.productQuantity);
        productImage = view.findViewById(R.id.productImage);
    }

    public void setProductName(String str) {
        productName.setText(str);
    }

    public void setProductQuantity(String str) {
        productQuantity.setText(str);
    }

    public void setProductImage(Integer imageId) {
        productImage.setImageResource(imageId);
    }

    public View getView() {
        return view;
    }
}
