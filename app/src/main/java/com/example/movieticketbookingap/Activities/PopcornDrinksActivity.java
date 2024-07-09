package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PopcornDrinksActivity extends AppCompatActivity {

    private TextView txtOriginalCount, txtCocaColaCount;
    private Button btnOrder;
    private DatabaseReference databaseReference;
    private ArrayList<GioHang> gioHangList; // ArrayList to store temporary orders

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popcorn_drinks);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("PopcornDrinkOrders");
        gioHangList = new ArrayList<>(); // Initialize the ArrayList

        txtOriginalCount = findViewById(R.id.txtOriginalCountValue);

        txtCocaColaCount = findViewById(R.id.txtCocaColaCountValue);


        setupIncrementDecrementButtons();

        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(v -> placeOrder());
    }

    private void setupIncrementDecrementButtons() {
        setupIncrementDecrement(R.id.btnMinusOriginal, R.id.btnPlusOriginal, txtOriginalCount);

        setupIncrementDecrement(R.id.btnMinusCocaCola, R.id.btnPlusCocaCola, txtCocaColaCount);

    }

    private void setupIncrementDecrement(int minusButtonId, int plusButtonId, TextView countTextView) {
        Button minusButton = findViewById(minusButtonId);
        Button plusButton = findViewById(plusButtonId);

        minusButton.setOnClickListener(v -> {
            int count = Integer.parseInt(countTextView.getText().toString());
            if (count > 0) {
                count--;
                countTextView.setText(String.valueOf(count));
            }
        });

        plusButton.setOnClickListener(v -> {
            int count = Integer.parseInt(countTextView.getText().toString());
            count++;
            countTextView.setText(String.valueOf(count));
        });
    }

    private void placeOrder() {
        int originalCount = Integer.parseInt(txtOriginalCount.getText().toString());

        int cocaColaCount = Integer.parseInt(txtCocaColaCount.getText().toString());


        if (originalCount == 0 && cocaColaCount == 0) {
            Toast.makeText(this, "Please select at least one item to order.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a GioHang object for this order
        GioHang gioHang = new GioHang("Movie Name", 0, null, "Popcorn and Drinks", 1); // Replace with actual movie name and total amount
        gioHang.setSoLuong(originalCount + cocaColaCount);

        gioHangList.add(gioHang); // Add the order to the ArrayList

        // Save the order to Firebase
        saveOrderToFirebase(gioHang);

        // Optionally clear the counts or show a confirmation message
        clearSelections();
        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
    }

    private void saveOrderToFirebase(GioHang gioHang) {
        // Generate a unique key for the order
        String orderId = databaseReference.push().getKey();
        // Set the order under this key in Firebase
        databaseReference.child(orderId).setValue(gioHang);
    }

    private void clearSelections() {
        txtOriginalCount.setText("0");

        txtCocaColaCount.setText("0");

    }
}
