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
    private Movie movie;
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
                intent.putExtra("movie", movie);
                startActivity(intent);
            }
        });
    }

    private void setVariable() {

        binding.backImg.setOnClickListener(v -> {
            finish();});
            //startActivity(new Intent(DetailActivity.this, MainActivity.class));
            Glide.with(DetailActivity.this)
                    .load(movie.getImage())
                    .into(binding.picDetail);
            binding.premiere.setText(movie.getPremiere());
            binding.movieTime.setText(movie.getTime());
            binding.name.setText(movie.getName());
            binding.genres.setText(movie.getGenre());
            binding.description.setText(movie.getDescription());
            binding.director.setText(movie.getDirector());
            binding.actor.setText(movie.getActors());
        }

    private void getIntentExtra() {
        movie = (Movie) getIntent().getSerializableExtra("movie");
    }
}