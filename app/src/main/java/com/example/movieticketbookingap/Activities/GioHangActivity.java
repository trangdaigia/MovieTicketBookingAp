package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.example.movieticketbookingap.Adapters.GioHangAdapter;
import com.example.movieticketbookingap.Adapters.UpComingAdapter;
import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.Domain.Movie;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivityGiohangBinding;
import com.example.movieticketbookingap.databinding.ActivityMainBinding;
import com.example.movieticketbookingap.databinding.ActivitySeatBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private ActivityGiohangBinding binding;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giohang);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        binding = ActivityGiohangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initGioHang();
    }

    private void initGioHang() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference myRef = database.getReference("GioHang").child(userId).child("Items");
            binding.recycleviewgiohang.setVisibility(View.VISIBLE);
            ArrayList<GioHang> list = new ArrayList<>();

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot issue : snapshot.getChildren()) {
                            GioHang gioHang = issue.getValue(GioHang.class);
                            if (gioHang != null) {
                                list.add(gioHang);
                            }
                        }
                        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this, list);
                        binding.recycleviewgiohang.setAdapter(adapter);
                        binding.recycleviewgiohang.setLayoutManager(new LinearLayoutManager(GioHangActivity.this));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("GioHang", "Lỗi khi đọc dữ liệu từ Firebase", error.toException());
                }
            });
        }
        /*DatabaseReference myRef = database.getReference("GioHang");
        binding.recycleviewgiohang.setVisibility(View.VISIBLE);
        ArrayList<GioHang> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        GioHang gioHang = issue.getValue(GioHang.class);
                        if (gioHang != null) {
                            list.add(gioHang);
                        }
                    }
                    GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this, list);
                    binding.recycleviewgiohang.setAdapter(adapter);
                    binding.recycleviewgiohang.setLayoutManager(new LinearLayoutManager(GioHangActivity.this));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
    }
}
