package com.example.foreverfvckid.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foreverfvckid.R;

public class NewArrivalViewHolder {

    private final TextView productName, productDesc, productPrice;
    private final ImageView productImage;
    private final View view;

    public NewArrivalViewHolder(Context context, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.view_holder_new_arrival, parent, false);
        productImage = view.findViewById(R.id.productImage);
        productName = view.findViewById(R.id.productName);
        productDesc = view.findViewById(R.id.productDesc);
        productPrice = view.findViewById(R.id.productPrice);
    }

    public void setProductName(String name) {
        productName.setText(name);
    }

    public void setProductDesc(String desc) {
        productDesc.setText(desc);
    }

    public void setProductPrice(String price) {
        productPrice.setText(price);
    }

    public void setProductImage(Integer imageId) {
        productImage.setImageResource(imageId);
    }

    public View getView() {
        return view;
    }
}
