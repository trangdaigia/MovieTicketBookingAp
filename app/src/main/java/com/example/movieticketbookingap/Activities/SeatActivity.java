package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.Domain.Movie;
import com.example.movieticketbookingap.Domain.Seat;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivitySeatBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeatActivity extends AppCompatActivity {
    ActivitySeatBinding binding;
    private int totalAmount = 0;
    private TextView totalTxt;
    private ArrayList<Seat> selectedSeats = new ArrayList<>();
    private Movie movie;
    private Button addBtn;

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

        // Find seats and set click listeners
        setupSeatClickListener(binding.A1);
        setupSeatClickListener(binding.A2);
        setupSeatClickListener(binding.A3);
        setupSeatClickListener(binding.A4);
        setupSeatClickListener(binding.A5);
        setupSeatClickListener(binding.A6);
        setupSeatClickListener(binding.A7);

        // Add button click listener
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeatActivity.this, GioHangActivity.class);
                intent.putExtra("selectedSeats", selectedSeats);
                intent.putExtra("movieName", movie.getName());
                startActivity(intent);
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
}
