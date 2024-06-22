package com.example.movieticketbookingap.Activities;



import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;

import java.util.ArrayList;

public class SeatBookActivity extends AppCompatActivity {

    private ArrayList<Button> selectedSeats = new ArrayList<>();
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_book);

        gridLayout = findViewById(R.id.gridLayout);
        setupSeats();

        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmSelection();
            }
        });
    }

    private void setupSeats() {
        int totalSeats = gridLayout.getChildCount();
        for (int i = 0; i < totalSeats; i++) {
            final Button seat = (Button) gridLayout.getChildAt(i);
            seat.setBackgroundColor(Color.LTGRAY);
            seat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleSeatSelection(seat);
                }
            });
        }
    }

    private void toggleSeatSelection(Button seat) {
        if (selectedSeats.contains(seat)) {
            seat.setBackgroundColor(Color.LTGRAY);
            selectedSeats.remove(seat);
        } else {
            seat.setBackgroundColor(Color.GREEN);
            selectedSeats.add(seat);
        }
    }

    private void confirmSelection() {
        if (selectedSeats.isEmpty()) {
            Toast.makeText(this, "No seats selected", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder seats = new StringBuilder("Selected Seats: ");
            for (Button seat : selectedSeats) {
                seats.append(seat.getText().toString()).append(" ");
            }
            Toast.makeText(this, seats.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

