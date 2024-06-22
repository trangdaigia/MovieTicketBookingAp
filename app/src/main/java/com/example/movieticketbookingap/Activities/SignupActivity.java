package com.example.movieticketbookingap.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText fullname,Email,Phone,username,Password,repassword;
    Button sigupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        sigupBtn = findViewById(R.id.signupBtn);
        mAuth = FirebaseAuth.getInstance();


        sigupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String repass = repassword.getText().toString();
                if (email.equals("")||password.equals("")||repassword.equals("")){
                    Toast.makeText(SignupActivity.this,"Hãy điền đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.equals(repass)){
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignupActivity.this,new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Toast.makeText(SignupActivity.this, "Dang Ky Thanh Cong.",
                                                    Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(SignupActivity.this, "Dang Ky That Bai.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}