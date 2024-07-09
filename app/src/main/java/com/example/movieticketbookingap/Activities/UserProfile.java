package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbookingap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;  // Mã yêu cầu để chọn hình ảnh
    private static final String PREFS_NAME = "UserPrefs";  // Tên tệp SharedPreferences
    private static final String IMAGE_URI_KEY = "image_uri";  // Khóa để lưu và truy xuất URI của hình ảnh

    ImageButton backbtn;
    ImageView imageView;
    ImageView contactbtn;
    ImageView changpwbtn;
    ImageView voucherbtn;
    ImageView PurchasedTbtn;
    TextView userEmailTextView;
    TextView userIdentifierTextView;  // TextView để hiển thị identifier

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();

        // Khởi tạo các view
        backbtn = findViewById(R.id.backbut);
        imageView = findViewById(R.id.changepic);
        contactbtn = findViewById(R.id.contact);
        changpwbtn = findViewById(R.id.repassword);
        voucherbtn = findViewById(R.id.voucher);
        PurchasedTbtn = findViewById(R.id.ticket);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        userIdentifierTextView = findViewById(R.id.userIdentifierTextView);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail(); // Lấy email của người dùng từ FirebaseAuth
            String userUid = currentUser.getUid(); // Lấy UID của người dùng từ FirebaseAuth

            // Hiển thị thông tin người dùng
            userEmailTextView.setText(userEmail);
            userIdentifierTextView.setText("ID User: " + userUid);
        } else {
            // Xử lý trường hợp không có người dùng đăng nhập
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
        }

        // Thiết lập sự kiện onClick cho các nút
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Contact.class));
            }
        });

        changpwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ChangePW.class));
            }
        });

        voucherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Vouchers.class));
            }
        });

        PurchasedTbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, ve_da_mua.class));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    // Phương thức để chọn hình ảnh từ thư viện
    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    // Xử lý kết quả trả về từ việc chọn hình ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);

            // Lưu URI hình ảnh vào SharedPreferences
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(IMAGE_URI_KEY, selectedImage.toString());
            editor.apply();

            Toast.makeText(this, "Đã cập nhật hình ảnh thành công", Toast.LENGTH_SHORT).show();
        }
    }
}
