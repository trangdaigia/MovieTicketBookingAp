package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;

public class ResetPassword extends AppCompatActivity {
    EditText user_name;
    Button reset;
    dbConnect DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        user_name = findViewById(R.id.usernameforget);
        reset = findViewById(R.id.resetBtn);
        DB = new dbConnect(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = user_name.getText().toString();
                Boolean checkuser = DB.checkusername(user);
                if (checkuser == true){
                    Intent intent = new Intent(ResetPassword.this,NewPassword.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                    startActivity(new Intent(ResetPassword.this,NewPassword.class).putExtra("username",user));
                }
                else{
                    Toast.makeText(ResetPassword.this, "Tên người dùng không tồn tại !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}