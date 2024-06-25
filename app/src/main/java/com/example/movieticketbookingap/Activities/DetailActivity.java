package com.example.movieticketbookingap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movieticketbookingap.Domain.Movie;
import com.example.movieticketbookingap.R;
import com.example.movieticketbookingap.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Movie object;
    Button seatbook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        setVariable();
        seatbook = findViewById(R.id.seatbook);
        seatbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, SeatActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setVariable() {

        binding.backImg.setOnClickListener(v -> {
            finish();});
            //startActivity(new Intent(DetailActivity.this, MainActivity.class));
            Glide.with(DetailActivity.this)
                    .load(object.getImage())
                    .into(binding.picDetail);
            binding.premiere.setText(object.getPremiere());
            binding.movieTime.setText(object.getTime());
            binding.name.setText(object.getName());
            binding.genres.setText(object.getGenre());
            binding.description.setText(object.getDescription());
            binding.director.setText(object.getDirector());
            binding.actor.setText(object.getActors());
        }

    private void getIntentExtra() {
        object = (Movie) getIntent().getSerializableExtra("object");
    }



}