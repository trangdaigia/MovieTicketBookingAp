package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.movieticketbookingap.R;

public class VeDaMuaActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView ticketInfoTextView;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ve_da_mua);

        back = findViewById(R.id.backButton);
        ticketInfoTextView = findViewById(R.id.ticketInfoTextView); // Thay đổi theo layout của bạn
        databaseReference = FirebaseDatabase.getInstance().getReference("USER_ID");

        // Lấy dữ liệu từ Firebase
        String userId = "YOUR_USER_ID"; // Thay đổi "YOUR_USER_ID" bằng ID người dùng thực tế
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VeDaMuaActivity.this, UserProfile.class));
            }
        });
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String movieName = dataSnapshot.child("movieName").getValue(String.class);
                    String selectedSeats = dataSnapshot.child("selectedSeats").getValue(String.class);
                    Long totalAmount = dataSnapshot.child("totalAmount").getValue(Long.class);

                    // Hiển thị dữ liệu
                    String ticketInfo = String.format("Phim: %s\nGhế: %s\nTổng số tiền: %d", movieName, selectedSeats, totalAmount);
                    ticketInfoTextView.setText(ticketInfo);
                } else {
                    ticketInfoTextView.setText("Không có dữ liệu");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có.
                ticketInfoTextView.setText("Lỗi: " + databaseError.getMessage());
            }
        });
    }
}
