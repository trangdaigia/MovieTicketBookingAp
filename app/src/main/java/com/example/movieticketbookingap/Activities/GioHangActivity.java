package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketbookingap.Adapters.GioHangAdapter;
import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivityGiohangBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private ActivityGiohangBinding binding;
    private DatabaseReference databaseReference;
    private TextView txtgiohangtrong;
    private Toolbar toolbar;
    private LinearLayout layout1;
    private Button btnmuahang;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGiohangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("GioHang");

        String userId = "USER_ID";  // Replace with actual user ID or session ID

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    GioHang gioHang = snapshot.getValue(GioHang.class);

                    if (gioHang != null) {
                        ArrayList<GioHang> list = new ArrayList<>();
                        list.add(gioHang);

                        binding.recycleviewgiohang.setLayoutManager(new LinearLayoutManager(GioHangActivity.this));
                        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this, list);
                        binding.recycleviewgiohang.setAdapter(adapter);

                        binding.txttongtien.setText(gioHang.getTotalAmount() + "đ");
                    } else {
                        binding.txtgiohangtrong.setVisibility(View.VISIBLE);
                        binding.recycleviewgiohang.setVisibility(View.GONE);
                    }
                } else {
                    binding.txtgiohangtrong.setVisibility(View.VISIBLE);
                    binding.recycleviewgiohang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("GioHang", "Failed to read data", error.toException());
            }
        });

        btnmuahang = findViewById(R.id.btnmuahang);
        binding.btnmuahang.setOnClickListener(v -> {
            Toast.makeText(GioHangActivity.this, "Đã đặt vé", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
