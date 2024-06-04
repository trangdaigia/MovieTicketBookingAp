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

public class NewPassword extends AppCompatActivity {
    EditText username,newpass,repass;
    Button resetBtn;
   dbConnect DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_password);

        username = findViewById(R.id.username_reset_text);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        newpass = findViewById(R.id.newpassword);
        repass = findViewById(R.id.repassword1);
        resetBtn = findViewById(R.id.resetpass);
        DB = new dbConnect(this);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = newpass.getText().toString();
                String re_pass = repass.getText().toString();

                if (pass.equals(re_pass)){
                    Boolean check_pass_update = DB.updatepassword(user,pass);
                    if (check_pass_update==true){
                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent1);
                        Toast.makeText(NewPassword.this, "Mật khẩu đã được đặt lại", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(NewPassword.this, "Đặt lại mật khẩu thất bại !!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(NewPassword.this, "Mật khẩu không trùng khớp !!", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}