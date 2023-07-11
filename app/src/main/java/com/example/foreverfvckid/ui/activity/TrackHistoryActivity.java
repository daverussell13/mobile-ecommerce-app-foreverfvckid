package com.example.foreverfvckid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.model.Transaction;
import com.example.foreverfvckid.ui.adapter.TransactionAdapter;

public class TrackHistoryActivity extends AppCompatActivity {

    private RecyclerView transactionRv;
    private SearchView searchView;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_history);
        initUI();
        initHandlers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TransactionAdapter transactionAdapter = new TransactionAdapter(this, Transaction.getTransactions());
        transactionRv.setLayoutManager(new LinearLayoutManager(this));
        transactionRv.setAdapter(transactionAdapter);
    }

    private void initUI() {
        transactionRv = findViewById(R.id.transactionRv);
        backBtn = findViewById(R.id.backButton);
        searchView = findViewById(R.id.searchView);
    }

    private void initHandlers() {
        backBtn.setOnClickListener(v -> onBackPressed());
    }
}