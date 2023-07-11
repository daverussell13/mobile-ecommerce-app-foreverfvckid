package com.example.foreverfvckid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.Product;
import com.example.foreverfvckid.ui.adapter.OurProductAdapter;
import com.example.foreverfvckid.ui.adapter.TransactionAdapter;

public class OurProductActivity extends AppCompatActivity {

    private Button backBtn;
    private SearchView searchView;
    private RecyclerView rvOurProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_product);
        initUI();
        initHandlers();
    }

    private void initUI() {
        backBtn = findViewById(R.id.backButton);
        searchView = findViewById(R.id.searchView);
        rvOurProducts = findViewById(R.id.rv_our_products);
    }

    private void initHandlers() {
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        OurProductAdapter adapter = new OurProductAdapter(this, Product.getOurProducts());
        rvOurProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvOurProducts.setAdapter(adapter);
    }
}