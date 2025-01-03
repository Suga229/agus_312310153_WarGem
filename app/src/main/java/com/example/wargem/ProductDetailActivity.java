package com.example.wargem;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private ArrayList<String> cartItems = new ArrayList<>();
    private int totalPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Aktifkan tombol kembali
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail Produk");
        }

        // Inisialisasi View
        ImageView imgProductDetail = findViewById(R.id.imgProductDetail);
        TextView tvProductNameDetail = findViewById(R.id.tvProductNameDetail);
        TextView tvProductPriceDetail = findViewById(R.id.tvProductPriceDetail);
        TextView tvProductDescription = findViewById(R.id.tvProductDescription);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnBuyNow = findViewById(R.id.btnBuyNow);
        ImageButton btnCart = findViewById(R.id.btnCart);

        // Ambil data yang dikirim dari halaman sebelumnya
        String productName = getIntent().getStringExtra("PRODUCT_NAME");
        String productPrice = getIntent().getStringExtra("PRODUCT_PRICE");
        int productImage = getIntent().getIntExtra("PRODUCT_IMAGE", 0);
        String productDescription = getIntent().getStringExtra("PRODUCT_DESCRIPTION");

        // Set data ke view
        tvProductNameDetail.setText(productName);
        tvProductPriceDetail.setText(productPrice);
        tvProductDescription.setText(productDescription);
        imgProductDetail.setImageResource(productImage);


        // Tombol Tambah ke Keranjang
        btnAddToCart.setOnClickListener(v -> {
            cartItems.add(productName + " - Rp " + productPrice);
            totalPrice += Integer.parseInt(productPrice.replace("Rp ", "").replace(".", ""));
            Toast.makeText(this, productName + " ditambahkan ke keranjang.", Toast.LENGTH_SHORT).show();
        });


        // Tombol Beli Produk
        btnBuyNow.setOnClickListener(v -> {
            Intent intent = new Intent(this, CheckoutActivity.class);

            // Kirim data produk ke halaman checkout
            ArrayList<String> checkoutItems = new ArrayList<>();
            checkoutItems.add(productName + " - Rp " + productPrice);

            intent.putStringArrayListExtra("CHECKOUT_ITEMS", checkoutItems);
            intent.putExtra("TOTAL_PRICE", Integer.parseInt(productPrice.replace("Rp ", "").replace(".", "")));
            startActivity(intent);
        });

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putStringArrayListExtra("CART_ITEMS", cartItems);
            intent.putExtra("TOTAL_PRICE", totalPrice);
            startActivity(intent);
        });


    }

    // Tangani tombol kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Kembali ke aktivitas sebelumnya
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
