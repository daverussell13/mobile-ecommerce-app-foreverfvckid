package com.example.foreverfvckid.data.model;

public class PurchasedProduct {

    private final Product product;
    private final Integer quantity;
    private final Double pricePerProduct;

    public PurchasedProduct(Product product, Integer quantity, Double pricePerProduct) {
        this.product = product;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPricePerProduct() {
        return pricePerProduct;
    }

    public Double calculateTotalPrice() {
        return product.getPrice() * quantity;
    }
}
