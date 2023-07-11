package com.example.foreverfvckid.data.model;

import com.example.foreverfvckid.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Transaction {

    private final Integer transactionId;
    private final User user;
    private final String transactionDate;
    private final String transactionStatus;
    private final ArrayList<PurchasedProduct> purchasedProducts;

    private static final Transaction[] transactions = {
            new Transaction(1, User.getDummyUser().get(0), "12 January 2023", "Completed", new ArrayList<>(Arrays.asList(
                    new PurchasedProduct(Product.getOurProducts().get(9), 1, Product.getOurProducts().get(9).getPrice()),
                    new PurchasedProduct(Product.getOurProducts().get(2), 3, Product.getOurProducts().get(2).getPrice()),
                    new PurchasedProduct(Product.getOurProducts().get(1), 1, Product.getOurProducts().get(1).getPrice())
            ))),
            new Transaction(2, User.getDummyUser().get(0), "7 May 2023", "Completed", new ArrayList<>(Collections.singletonList(
                    new PurchasedProduct(Product.getOurProducts().get(3), 2, Product.getOurProducts().get(3).getPrice())
            ))),
            new Transaction(3, User.getDummyUser().get(0), "29 December 2023", "Canceled", new ArrayList<>(Arrays.asList(
                    new PurchasedProduct(Product.getOurProducts().get(12), 2, Product.getOurProducts().get(12).getPrice()),
                    new PurchasedProduct(Product.getOurProducts().get(2), 1, Product.getOurProducts().get(2).getPrice())
            ))),
            new Transaction(4, User.getDummyUser().get(0), "29 December 2023", "Completed", new ArrayList<>(Collections.singletonList(
                    new PurchasedProduct(Product.getOurProducts().get(5), 1, Product.getOurProducts().get(5).getPrice())
            )))
    };

    public Transaction(
            Integer transactionId,
            User user,
            String transactionDate,
            String transactionStatus,
            ArrayList<PurchasedProduct> purchasedProducts
    ) {
        this.transactionId = transactionId;
        this.user = user;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.purchasedProducts = purchasedProducts;
    }

    public static ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(Arrays.asList(transactions));
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public User getUser() {
        return user;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for (PurchasedProduct purchasedProduct : purchasedProducts)
            totalPrice += purchasedProduct.calculateTotalPrice();
        return totalPrice;
    }

    public String getFormattedTotalPrice() {
        return StringUtils.formatPriceRupiah(getTotalPrice());
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public ArrayList<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }
}
