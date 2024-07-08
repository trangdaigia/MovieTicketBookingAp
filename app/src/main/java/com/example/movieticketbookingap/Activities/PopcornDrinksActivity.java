package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PopcornDrinksActivity extends AppCompatActivity {

    private TextView txtOriginalCount, txtCheeseCount, txtCaramelCount, txtCocaColaCount, txtSpriteCount, txtFantaCount;
    private Button btnOrder;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popcorn_drinks);
        
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("PopcornDrinkOrders");

        txtOriginalCount = findViewById(R.id.txtOriginalCountValue);
        txtCheeseCount = findViewById(R.id.txtCheeseCountValue);
        txtCaramelCount = findViewById(R.id.txtCaramelCountValue);
        txtCocaColaCount = findViewById(R.id.txtCocaColaCountValue);
        txtSpriteCount = findViewById(R.id.txtSpriteCountValue);
        txtFantaCount = findViewById(R.id.txtFantaCountValue);

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
        int originalCount = Integer.parseInt(txtOriginalCount.getText().toString());
        int cheeseCount = Integer.parseInt(txtCheeseCount.getText().toString());
        int caramelCount = Integer.parseInt(txtCaramelCount.getText().toString());
        int cocaColaCount = Integer.parseInt(txtCocaColaCount.getText().toString());
        int spriteCount = Integer.parseInt(txtSpriteCount.getText().toString());
        int fantaCount = Integer.parseInt(txtFantaCount.getText().toString());

        Map<String, Integer> items = new HashMap<>();

        if (originalCount > 0) {
            items.put("originalCount", originalCount);
        }
        if (cheeseCount > 0) {
            items.put("cheeseCount", cheeseCount);
        }
        if (caramelCount > 0) {
            items.put("caramelCount", caramelCount);
        }
        if (cocaColaCount > 0) {
            items.put("cocaColaCount", cocaColaCount);
        }
        if (spriteCount > 0) {
            items.put("spriteCount", spriteCount);
        }
        if (fantaCount > 0) {
            items.put("fantaCount", fantaCount);
        }

        if (!items.isEmpty()) {
            databaseReference.push().setValue(items).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(PopcornDrinksActivity.this, "Đã thêm bắp nước vào giỏ hàng", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PopcornDrinksActivity.this, "Không thể thêm bắp nước vào giỏ hàng", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(PopcornDrinksActivity.this, "Vui lòng chọn món", Toast.LENGTH_LONG).show();
        }
    }
}
