package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.movieticketbookingap.Adapters.GioHangAdapter;
import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivityGiohangBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private ActivityGiohangBinding binding;
    private DatabaseReference databaseReference;
    private TextView txtgiohangtrong;
    private Toolbar toolbar;
    private LinearLayout layout1;
    private Button btnmuahang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize View Binding
        binding = ActivityGiohangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("GioHang");

        anhxa();
        initGioHang();
    }

    private void anhxa() {
        txtgiohangtrong = binding.txtgiohangtrong;
        toolbar = binding.toolbar;
        layout1 = binding.layout1;
        btnmuahang = binding.btnmuahang;
    }

    private void initGioHang() {
        // Lấy dữ liệu từ Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<GioHang> list = new ArrayList<>();
                int totalAmount = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    GioHang gioHang = snapshot.getValue(GioHang.class);
                    if (gioHang != null) {
                        list.add(gioHang);
                        totalAmount += gioHang.getTotalAmount();
                    }
                }

                if (list.isEmpty()) {
                    binding.txtgiohangtrong.setVisibility(View.VISIBLE);
                    binding.recycleviewgiohang.setVisibility(View.GONE);
                } else {
                    binding.txtgiohangtrong.setVisibility(View.GONE);
                    binding.recycleviewgiohang.setVisibility(View.VISIBLE);

                    GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this, list);
                    binding.recycleviewgiohang.setAdapter(adapter);
                    binding.recycleviewgiohang.setLayoutManager(new LinearLayoutManager(GioHangActivity.this));
                }

                binding.txttongtien.setText(totalAmount + "Đ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("GioHang", "Không thể lấy được dữ liệu từ giỏ hàng", databaseError.toException());
            }
        });
    }
}
