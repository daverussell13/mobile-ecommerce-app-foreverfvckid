package com.example.foreverfvckid.data.model;

import com.example.foreverfvckid.R;

import java.util.ArrayList;

public class ProductBrand {

    private final Integer id;
    private final String name;

    public static ArrayList<ProductBrand> getProductBrand() {
        ArrayList<ProductBrand> productBrands = new ArrayList<>();
        productBrands.add(new ProductBrand(1, "Anti Social Social Club"));
        productBrands.add(new ProductBrand(2, "Balenciaga"));
        productBrands.add(new ProductBrand(3, "A Bathing Ape"));
        productBrands.add(new ProductBrand(4, "Fear Of God"));
        productBrands.add(new ProductBrand(5, "Off White"));
        productBrands.add(new ProductBrand(6, "Supreme"));
        productBrands.add(new ProductBrand(7, "Thrasher"));
        productBrands.add(new ProductBrand(8, "Vlone"));
        return productBrands;
    };

    public ProductBrand(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer resolveImageId() {
        switch (id) {
            case 1:
                return R.drawable.brand_assc;
            case 2:
                return R.drawable.brand_balenciaga;
            case 3:
                return R.drawable.brand_bape;
            case 4:
                return R.drawable.brand_fog;
            case 5:
                return R.drawable.brand_offwhite;
            case 6:
                return R.drawable.brand_supreme;
            case 7:
                return R.drawable.brand_thrasher;
            case 8:
                return R.drawable.brand_vlone;
        }
        return -1;
    }

    public String getName() {
        return name;
    }
}
