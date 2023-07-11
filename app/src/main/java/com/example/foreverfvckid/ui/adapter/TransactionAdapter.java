package com.example.foreverfvckid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.PurchasedProduct;
import com.example.foreverfvckid.data.model.Transaction;
import com.example.foreverfvckid.ui.holder.PurchasedProductViewHolder;

import java.util.ArrayList;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private final Context context;
    private final ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.transactionDate.setText(transaction.getTransactionDate());
        holder.transactionStatus.setText(transaction.getTransactionStatus());
        holder.totalPrice.setText(transaction.getFormattedTotalPrice());
        inflatePurchasedProducts(holder.purchasedProducts, transaction);
    }

    private void inflatePurchasedProducts(ViewGroup parent, Transaction transaction) {
        for (PurchasedProduct purchasedProduct : transaction.getPurchasedProducts()) {
            PurchasedProductViewHolder holder = new PurchasedProductViewHolder(context, parent);
            holder.setProductName(purchasedProduct.getProduct().getName());
            holder.setProductQuantity(getFormattedPurchasedProductQuantity(purchasedProduct.getQuantity()));
            holder.setProductImage(purchasedProduct.getProduct().resolveImageId());
            parent.addView(holder.getView());
        }
    }

    private String getFormattedPurchasedProductQuantity(Integer quantity) {
        if (quantity > 1) return String.format(Locale.getDefault(), "%d items", quantity);
        return String.format(Locale.getDefault(), "%d item", quantity);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        private final TextView transactionDate;
        private final TextView transactionStatus;
        private final TextView totalPrice;
        private final LinearLayout purchasedProducts;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionDate = itemView.findViewById(R.id.transactionDate);
            transactionStatus = itemView.findViewById(R.id.transactionStatus);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            purchasedProducts = itemView.findViewById(R.id.purchasedProducts);
        }
    }
}
