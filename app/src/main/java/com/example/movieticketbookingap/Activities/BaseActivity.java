package com.example.movieticketbookingap.Activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


    public class BaseActivity extends AppCompatActivity {
        FirebaseDatabase database;

        public String TAG="uilover";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            database=FirebaseDatabase.getInstance();
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            Window w=getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
