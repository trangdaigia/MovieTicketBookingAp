package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieticketbookingap.Domain.Movie;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivitySeatBinding;

public class SeatActivity extends AppCompatActivity {
    ActivitySeatBinding binding;
    private int totalAmount = 0;
    private TextView totalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySeatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        totalTxt = findViewById(R.id.totalTxt);

        // Find seats and set click listeners
        TextView seatA1 = findViewById(R.id.A1);
        TextView seatA2 = findViewById(R.id.A2);
        TextView seatA3 = findViewById(R.id.A3);
        TextView seatA4 = findViewById(R.id.A4);
        TextView seatA5 = findViewById(R.id.A5);
        TextView seatA6 = findViewById(R.id.A6);
        TextView seatA7 = findViewById(R.id.A7);

        // Add more seats as needed

        seatA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });

        seatA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });
        seatA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });
        seatA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });
        seatA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });
        seatA6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });
        seatA7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    updateTotalAmount(-45000);
                } else {
                    v.setSelected(true);
                    updateTotalAmount(45000);
                }
            }
        });
        // Set click listeners for other seats in a similar way
    }



    private void updateTotalAmount(int amount) {
        totalAmount += amount;
        totalTxt.setText(totalAmount + "đ");
    }

}