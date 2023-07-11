package com.example.foreverfvckid.data.model;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Product {

    private final ProductBrand brand;
    private final Integer id;
    private final String name;
    private final String desc;
    private final Double price;

    private static final Product[] ourProducts = {
            new Product(ProductBrand.getProductBrand().get(0), 1, "ASSC Find Me Tee", "BNIB langsung checkout aja bro", 1700000.0),
            new Product(ProductBrand.getProductBrand().get(0),2, "ASSC X Fragment Bolt Hoodie", "BNIB langsung checkout aja bro", 2000000.0),
            new Product(ProductBrand.getProductBrand().get(1), 3, "Balenciaga Crypto Outline Trasksuit in Black Faded", "BNIB langsung checkout aja bro", 6000000.0),
            new Product(ProductBrand.getProductBrand().get(1), 4, "Balenciaga Outline Tracksuit Jacket in Black", "BNIB langsung checkout aja bro", 7200000.0),
            new Product(ProductBrand.getProductBrand().get(2), 5, "BAPE Multi Camo NYC Logo Shark Full Zip", "BNIB langsung checkout aja bro", 5000000.0),
            new Product(ProductBrand.getProductBrand().get(2), 6, "BAPE Reflective Print Tee", "BNIB langsung checkout aja bro", 7350000.0),
            new Product(ProductBrand.getProductBrand().get(3), 7, "Fear of God Essentials Core Hoodie", "BNIB langsung checkout aja bro", 2500000.0),
            new Product(ProductBrand.getProductBrand().get(3), 8, "Fear of God Essentials S/S T-shirt", "BNIB langsung checkout aja bro", 1200000.0),
            new Product(ProductBrand.getProductBrand().get(4), 9, "Off White Big Bookish Skate Tee", "BNIB langsung checkout aja bro", 3500000.0),
            new Product(ProductBrand.getProductBrand().get(4), 10, "Off White Chicago Bulls Blue Varsity", "BNIB langsung checkout aja bro", 4750000.0),
            new Product(ProductBrand.getProductBrand().get(5), 11, "Supreme 3000 Photograph Print T-shirt", "BNIB langsung checkout aja bro", 7000000.0),
            new Product(ProductBrand.getProductBrand().get(5), 12, "Supreme Box Logo Hoodie", "BNIB langsung checkout aja bro", 7800000.0),
            new Product(ProductBrand.getProductBrand().get(6), 13, "Thrasher Believe Hoodie", "BNIB langsung checkout aja bro", 1200000.0),
            new Product(ProductBrand.getProductBrand().get(6), 14, "Thrasher Sketch Shirt", "BNIB langsung checkout aja bro", 700000.0),
            new Product(ProductBrand.getProductBrand().get(7), 15, "Vlone X Juice WRLD 999 World Tee", "BNIB langsung checkout aja bro", 3500000.0),
            new Product(ProductBrand.getProductBrand().get(7), 16, "Vlone X Juice WRLD XO Joker Hoodie", "BNIB langsung checkout aja bro", 5300000.0),
    };

    private static final Product[] newArrival = {
            ourProducts[12],
            ourProducts[6],
            ourProducts[15],
            ourProducts[1],
            ourProducts[3],
            ourProducts[9]
    };

    public static ArrayList<Product> getNewArrival() {
        return new ArrayList<>(Arrays.asList(newArrival));
    }

    public static ArrayList<Product> getOurProducts() {
        return new ArrayList<>(Arrays.asList(ourProducts));
    }

    public Product(ProductBrand productBrand, Integer productId, String name, String desc, Double price) {
        this.brand = productBrand;
        this.id = productId;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Integer resolveImageId() {
        switch (id) {
            case 1:
                return R.drawable.product_assc_find_me_tee;
            case 2:
                return R.drawable.product_assc_x_fragment_bolt_hoodie;
            case 3:
                return R.drawable.product_balenciaga_crypto_tshirt_oversized_in_black_faded;
            case 4:
                return R.drawable.product_balenciaga_outline_tracksuit_jacket_in_black;
            case 5:
                return R.drawable.product_bape_multi_camo_nyc_logo_shart_full_zip;
            case 6:
                return R.drawable.product_bape_reflective_print_tee;
            case 7:
                return R.drawable.product_fog_essentials_core_hoodie;
            case 8:
                return R.drawable.product_fog_essentials_core_ss_tshirt;
            case 9:
                return R.drawable.product_offwhite_big_bookish_skate_tee;
            case 10:
                return R.drawable.product_offwhite_chicago_bulls_blue_varsity;
            case 11:
                return R.drawable.product_supreme_3000_photograph_print_tshirt;
            case 12:
                return R.drawable.product_supreme_box_logo_hoodie;
            case 13:
                return R.drawable.product_thrasher_believe_hoodie;
            case 14:
                return R.drawable.product_thrasher_sketch_tshirt;
            case 15:
                return R.drawable.product_vlone_juice_wrld_999_world_tee;
            case 16:
                return R.drawable.product_vlone_juice_wrld_xo_joker_hoodie;
            default:
                return -1;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        return StringUtils.formatPriceRupiah(this.price);
    }

    public ProductBrand getBrand() {
        return brand;
    }
}

