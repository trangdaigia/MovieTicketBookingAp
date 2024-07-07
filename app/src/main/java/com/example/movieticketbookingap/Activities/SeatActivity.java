package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.Domain.Movie;
import com.example.movieticketbookingap.Domain.Seat;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivitySeatBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

public class SeatActivity extends AppCompatActivity {
    ActivitySeatBinding binding;
    private int totalAmount = 0;
    private TextView totalTxt;
    private ArrayList<Seat> selectedSeats = new ArrayList<>();
    private Movie movie;
    private Button addBtn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movie = (Movie) getIntent().getSerializableExtra("movie");
        if (movie != null) {
            String movieName = movie.getName();
            binding.TitleMovie.setText(movieName);
        }

        totalTxt = findViewById(R.id.totalTxt);

        setupSeatClickListener(binding.A1);
        setupSeatClickListener(binding.A2);
        setupSeatClickListener(binding.A3);
        setupSeatClickListener(binding.A4);
        setupSeatClickListener(binding.A5);
        setupSeatClickListener(binding.A6);
        setupSeatClickListener(binding.A7);

        addBtn = findViewById(R.id.addBtn);
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDataToFirebase();
            }
        });
    }

    private void setupSeatClickListener(final TextView seat) {
        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                    selectedSeats.remove(new Seat(seat.getText().toString()));
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                    selectedSeats.add(new Seat(seat.getText().toString()));
                }
            }
        });
    }

    private void updateTotalAmount(int amount) {
        totalAmount += amount;
        totalTxt.setText(totalAmount + "đ");
    }

    private void insertDataToFirebase() {
        // Tạo một đối tượng DatabaseReference để trỏ đến vị trí trong Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("GioHang");

// Tạo một đối tượng để lưu trữ dữ liệu
        Map<String, Object> gioHang = new HashMap<>();
        gioHang.put("movieName", movie.getName());
        gioHang.put("totalAmount", totalAmount);
        gioHang.put("selectedSeats", selectedSeats);

// Đẩy dữ liệu lên Firebase
        databaseReference.push().setValue(gioHang)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Firebase", "Data added successfully");
                        Toast.makeText(SeatActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Firebase", "Error adding data", task.getException());
                        Toast.makeText(SeatActivity.this, "Lỗi, không thể thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
