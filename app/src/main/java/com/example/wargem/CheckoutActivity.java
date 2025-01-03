package com.example.wargem;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ArrayList<String> checkoutItems = new ArrayList<>();
    private TextView tvCheckoutTotalPrice, tvEmptyCheckout;
    private LinearLayout llCheckoutItems;
    private Spinner spinnerPaymentMethod;
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarCheckout);
        setSupportActionBar(toolbar);

        // Aktifkan tombol kembali
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Checkout");
        }

        // Inisialisasi View
        llCheckoutItems = findViewById(R.id.llCheckoutItems);
        tvCheckoutTotalPrice = findViewById(R.id.tvCheckoutTotalPrice);
        tvEmptyCheckout = findViewById(R.id.tvEmptyCheckout);
        spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);

        // Ambil data dari intent
        if (getIntent().hasExtra("CHECKOUT_ITEMS")) {
            checkoutItems = getIntent().getStringArrayListExtra("CHECKOUT_ITEMS");
            totalPrice = getIntent().getIntExtra("TOTAL_PRICE", 0);
        }

        // Tampilkan daftar item
        if (checkoutItems.isEmpty()) {
            tvEmptyCheckout.setVisibility(View.VISIBLE);
        } else {
            tvEmptyCheckout.setVisibility(View.GONE);
            for (String item : checkoutItems) {
                TextView textView = new TextView(this);
                textView.setText(item);
                textView.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.black));
                llCheckoutItems.addView(textView);
            }
        }

        // Tampilkan total harga
        tvCheckoutTotalPrice.setText("Total: Rp " + totalPrice);

        // Konfigurasi metode pembayaran
        configurePaymentMethodSpinner();

        // Tombol Konfirmasi
        Button btnConfirmCheckout = findViewById(R.id.btnConfirmCheckout);
        btnConfirmCheckout.setOnClickListener(v -> confirmCheckout());
    }

    private void configurePaymentMethodSpinner() {
        String[] paymentMethods = {"Transfer Bank", "Kartu Kredit", "Dompet Digital"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, paymentMethods);
        spinnerPaymentMethod.setAdapter(adapter);
    }

    private void confirmCheckout() {
        String selectedPaymentMethod = spinnerPaymentMethod.getSelectedItem().toString();
        if (selectedPaymentMethod.isEmpty()) {
            Toast.makeText(this, "Silakan pilih metode pembayaran.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pembayaran berhasil menggunakan " + selectedPaymentMethod, Toast.LENGTH_LONG).show();
            finish(); // Simulasikan pembayaran selesai
        }
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
