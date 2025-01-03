package com.example.wargem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout;
    ImageButton btnCart, btnChat, btnHome, btnTransaction, btnProfile;
    EditText edtSearch;
    RecyclerView recyclerViewRecommendations;
    RecommendationAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewRecommendations = findViewById(R.id.recyclerViewRecommendations);
        recyclerViewRecommendations.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Product(
                "NOIR M1 M-1",
                "Rp 299.999",
                R.drawable.mouseg,
                "Fitur:" + " -Sensor Pixart PAW3311"
        ));
        productList.add(new Product(
                "Ajazz AK820 GTS PRO 75%",
                "Rp 399.000",
                R.drawable.keyboards,
                "Barang 2 hadir dengan fitur canggih untuk memberikan pengalaman terbaik."
        ));
        productList.add(new Product(
                "Fantech EOS PRO WGP15",
                "Rp 429.000",
                R.drawable.gamepads,
                "WGP15 (Wireless) : - Support PC, Switch, iOS, MacOS, tvOS, Android, & Tesla Vehicles"
        ));
        productList.add(new Product(
                "Headset Wireless GO Tune WH06 - WH06b",
                "Rp 259.000",
                R.drawable.headphones,
                "WH06 dibekali dengan kombinasi mode konektivitas yang sempurna. Terdiri dari mode wired menggunakan kabel 3.5 mm dan mode wireless"
        ));


        adapter = new RecommendationAdapter(this, productList);
        recyclerViewRecommendations.setAdapter(adapter);

        ImageButton btnFeature1 = findViewById(R.id.btnFeature1);
        ImageButton btnFeature2 = findViewById(R.id.btnFeature2);
        ImageButton btnFeature3 = findViewById(R.id.btnFeature3);
        ImageButton btnFeature4 = findViewById(R.id.btnFeature4);

        btnFeature1.setOnClickListener(v ->
                Toast.makeText(this, "Mohon maaf fitur ini belum bisa dijalankan", Toast.LENGTH_SHORT).show()
        );

        btnFeature2.setOnClickListener(v ->
                Toast.makeText(this, "Mohon maaf fitur ini belum bisa dijalankan", Toast.LENGTH_SHORT).show()
        );

        btnFeature3.setOnClickListener(v ->
                Toast.makeText(this, "Mohon maaf fitur ini belum bisa dijalankan", Toast.LENGTH_SHORT).show()
        );

        btnFeature4.setOnClickListener(v ->
                Toast.makeText(this, "Mohon maaf fitur ini belum bisa dijalankan", Toast.LENGTH_SHORT).show()
        );

        // Inisialisasi
        btnLogout = findViewById(R.id.btnLogout);
        btnCart = findViewById(R.id.btnCart);
        btnChat = findViewById(R.id.btnChat);
        edtSearch = findViewById(R.id.edtSearch);
        btnHome = findViewById(R.id.btnHome);
        btnTransaction = findViewById(R.id.btnTransaction);
        btnProfile = findViewById(R.id.btnProfile);

        // Logout
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });


        // Klik Keranjang
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });

        // Klik Chat
        btnChat.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Navigasi ke Chat", Toast.LENGTH_SHORT).show()
        );

        // Search
        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            String query = edtSearch.getText().toString();
            if (!query.isEmpty()) {
                Toast.makeText(HomeActivity.this, "Mencari: " + query, Toast.LENGTH_SHORT).show();
                // Tambahkan logika pencarian di sini
            }
            return false;
        });

        // Klik Home
        btnHome.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Berada di Halaman Home", Toast.LENGTH_SHORT).show()
        );

        // Klik Transaksi
        btnTransaction.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Navigasi ke Transaksi", Toast.LENGTH_SHORT).show()
        );

        // Klik Profile
        btnProfile.setOnClickListener(v ->
                Toast.makeText(HomeActivity.this, "Navigasi ke Profile", Toast.LENGTH_SHORT).show()
        );

    }

}


