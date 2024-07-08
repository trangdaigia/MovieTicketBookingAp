package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieticketbookingap.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;


public class editprofile extends AppCompatActivity {
    ImageView back_btn;
    ImageView profilePic;
    EditText nicknameInput;
    EditText emailInput;
    Button updateProfile;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        back_btn =findViewById(R.id.back_user);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(editprofile.this, UserProfile.class));
            }
        });

        profilePic =findViewById(R.id.change_avatar);
        nicknameInput =findViewById(R.id.nickname_edit);
        emailInput =findViewById(R.id.email_edit);
        updateProfile =findViewById(R.id.update_profile);
        progressBar =findViewById(R.id.progress_update);
    }

}