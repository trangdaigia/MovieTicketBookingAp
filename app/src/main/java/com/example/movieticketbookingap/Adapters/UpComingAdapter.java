package com.example.movieticketbookingap.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.movieticketbookingap.Activities.DetailActivity;
import com.example.movieticketbookingap.Domain.Movie;
import com.example.movieticketbookingap.R;

import java.util.ArrayList;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.ViewHolder> {
    private ArrayList<Movie> items;
    private Context context;

    public UpComingAdapter(ArrayList<Movie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public UpComingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_film,parent,false);
        return new UpComingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingAdapter.ViewHolder holder, int position) {
        Movie movie = items.get(position);
        holder.titleTxt.setText(movie.getName());
        Glide.with(context)
                .load(movie.getImage())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movie",items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
