package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieticketbookingap.R;

public class UserProfile extends AppCompatActivity {
    AppCompatButton back_edit;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        backbtn = findViewById(R.id.backbut);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });
        back_edit=findViewById(R.id.edit_profile);
        back_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(UserProfile.this,editprofile.class));
                    }
                });

    }
}