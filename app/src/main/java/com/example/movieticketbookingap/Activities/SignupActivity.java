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

public class SignupActivity extends AppCompatActivity {
    EditText fullname,Email,Phone,username,password,repassword;
    Button sigupBtn;
    dbConnect DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        fullname = findViewById(R.id.fullname);
        Email = findViewById(R.id.Email);
        Phone= findViewById(R.id.Phone);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        sigupBtn = findViewById(R.id.signupBtn);


        sigupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbConnect DB = new dbConnect(SignupActivity.this);
                String Hoten = fullname.getText().toString();
                String Emailreg = Email.getText().toString();
                String Sdt = Phone.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (Hoten.equals("")||Emailreg.equals("")||Sdt.equals("")||user.equals("")||pass.equals("")||repassword.equals("")){
                    Toast.makeText(SignupActivity.this,"Hãy điền đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(Hoten,Emailreg,Sdt,user,pass);
                            if ( insert == true){
                                Toast.makeText(SignupActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(SignupActivity.this, "Tài Khoản Đã Tồn Tại !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}