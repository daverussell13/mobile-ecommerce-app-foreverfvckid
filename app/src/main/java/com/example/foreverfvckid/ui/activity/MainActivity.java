package com.example.foreverfvckid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.Product;
import com.example.foreverfvckid.data.model.ProductBrand;
import com.example.foreverfvckid.data.model.User;
import com.example.foreverfvckid.data.session.UserSession;
import com.example.foreverfvckid.ui.adapter.ProductBrandAdapter;
import com.example.foreverfvckid.ui.holder.NewArrivalViewHolder;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.carousel.CarouselLayoutManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvProductBrand;
    private GridLayout glNewArrival;
    private User authenticatedUser;
    private UserSession userSession;
    private TextView txtFullName;
    private Button btnLogout;
    private MaterialCardView mcvStoreLoc, mcvOurProducts, mcvTrackHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userSession = new UserSession(this);
        initUI();
        initHandlers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        authenticatedUser = userSession.getAuthenticatedUser();
        if (authenticatedUser == null) {
            startActivity(new Intent(this, LoginActivity.class)); finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtFullName.setText(authenticatedUser.getFullName());

        ProductBrandAdapter productBrandAdapter = new ProductBrandAdapter(this, ProductBrand.getProductBrand());
        rvProductBrand.setLayoutManager(new CarouselLayoutManager());
        rvProductBrand.setAdapter(productBrandAdapter);

        int productIndex = 0;
        for (int i = 0; i < glNewArrival.getRowCount(); i++) {
            for (int j = 0; j < glNewArrival.getColumnCount(); j++) {
                inflateNewArrivalProduct(Product.getNewArrival().get(productIndex++), i, j);
            }
        }
    }

    private void initUI() {
        rvProductBrand = findViewById(R.id.rv_product_brand);
        glNewArrival = findViewById(R.id.gl_new_arrival);
        txtFullName = findViewById(R.id.txt_user_full_name);
        btnLogout = findViewById(R.id.btn_logout);
        mcvStoreLoc = findViewById(R.id.mcv_street_loc);
        mcvOurProducts = findViewById(R.id.mcv_our_products);
        mcvTrackHistory = findViewById(R.id.mcv_track_history);
    }

    private void inflateNewArrivalProduct(Product newArrivalProduct, int rowIdx, int colIdx) {
        NewArrivalViewHolder holder = new NewArrivalViewHolder(this, glNewArrival);
        holder.setProductImage(newArrivalProduct.resolveImageId());
        holder.setProductName(newArrivalProduct.getName());
        holder.setProductDesc(newArrivalProduct.getDesc());
        holder.setProductPrice(newArrivalProduct.getFormattedPrice());

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.rowSpec = GridLayout.spec(rowIdx);
        layoutParams.columnSpec = GridLayout.spec(colIdx, 1f);
        layoutParams.width = 0;

        View view = holder.getView();
        view.setLayoutParams(layoutParams);
        glNewArrival.addView(view);
    }

    private void initHandlers() {
        btnLogout.setOnClickListener(v -> {
            userSession.removeUserSession();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        mcvStoreLoc.setOnClickListener(v ->
                startActivity(new Intent(this, StoreLocationActivity.class)));

        mcvOurProducts.setOnClickListener(v ->
                startActivity(new Intent(this, OurProductActivity.class)));

        mcvTrackHistory.setOnClickListener(v ->
                startActivity(new Intent(this, TrackHistoryActivity.class)));
    }
}