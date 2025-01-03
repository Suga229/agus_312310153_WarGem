package com.example.wargem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ArrayList<String> cartItems = new ArrayList<>();
    private TextView tvCartTotalPrice, tvEmptyCart;
    private LinearLayout llCartItems;
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbar);

        // Aktifkan tombol kembali
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Keranjang");
        }

        // Inisialisasi View
        llCartItems = findViewById(R.id.llCartItems);
        tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        tvEmptyCart = findViewById(R.id.tvEmptyCart);
        Button btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout);

        // Ambil data dari intent
        if (getIntent().hasExtra("CART_ITEMS")) {
            cartItems = getIntent().getStringArrayListExtra("CART_ITEMS");
            totalPrice = getIntent().getIntExtra("TOTAL_PRICE", 0);
        }

        // Tampilkan produk di keranjang
        if (cartItems.isEmpty()) {
            tvEmptyCart.setVisibility(View.VISIBLE);
        } else {
            tvEmptyCart.setVisibility(View.GONE);
            for (String item : cartItems) {
                TextView textView = new TextView(this);
                textView.setText(item);
                textView.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.black));
                llCartItems.addView(textView);
            }
        }

        // Tampilkan total harga
        tvCartTotalPrice.setText("Total: Rp " + totalPrice);

        btnProceedToCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(this, CheckoutActivity.class);
            intent.putStringArrayListExtra("CHECKOUT_ITEMS", cartItems);
            intent.putExtra("TOTAL_PRICE", totalPrice);
            startActivity(intent);
        });

    }

    // Tangani tombol kembali
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

