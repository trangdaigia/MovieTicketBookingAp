package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;

public class PopcornDrinksActivity extends AppCompatActivity {

    private TextView txtOriginalCount, txtCheeseCount, txtCaramelCount, txtCocaColaCount, txtSpriteCount, txtFantaCount;
    private Button btnOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popcorn_drinks);

        // Initialize TextViews for counts
        txtOriginalCount = findViewById(R.id.txtOriginalCountValue);
        txtCheeseCount = findViewById(R.id.txtCheeseCountValue);
        txtCaramelCount = findViewById(R.id.txtCaramelCountValue);
        txtCocaColaCount = findViewById(R.id.txtCocaColaCountValue);
        txtSpriteCount = findViewById(R.id.txtSpriteCountValue);
        txtFantaCount = findViewById(R.id.txtFantaCountValue);

        // Initialize Buttons for incrementing and decrementing
        setupIncrementDecrementButtons();

        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(v -> placeOrder());
    }

    private void setupIncrementDecrementButtons() {
        setupIncrementDecrement(R.id.btnMinusOriginal, R.id.btnPlusOriginal, txtOriginalCount);
        setupIncrementDecrement(R.id.btnMinusCheese, R.id.btnPlusCheese, txtCheeseCount);
        setupIncrementDecrement(R.id.btnMinusCaramel, R.id.btnPlusCaramel, txtCaramelCount);
        setupIncrementDecrement(R.id.btnMinusCocaCola, R.id.btnPlusCocaCola, txtCocaColaCount);
        setupIncrementDecrement(R.id.btnMinusSprite, R.id.btnPlusSprite, txtSpriteCount);
        setupIncrementDecrement(R.id.btnMinusFanta, R.id.btnPlusFanta, txtFantaCount);
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
        String message = "Bắp thường: " + txtOriginalCount.getText().toString() +
                "\nBắp phô mai: " + txtCheeseCount.getText().toString() +
                "\nBắp caramel: " + txtCaramelCount.getText().toString() +
                "\nCoca Cola: " + txtCocaColaCount.getText().toString() +
                "\nSprite: " + txtSpriteCount.getText().toString() +
                "\nFanta Cam: " + txtFantaCount.getText().toString();

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
