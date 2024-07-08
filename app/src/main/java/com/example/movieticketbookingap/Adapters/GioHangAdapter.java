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
import com.example.movieticketbookingap.Activities.DetailActivity;
import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.Domain.Seat;
import com.example.movieticketbookingap.R;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    private Context context;
    private ArrayList<GioHang> gioHangList;

    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.movieNameTxt.setText(gioHang.getMovieName());
        holder.totalAmountTxt.setText(gioHang.getTotalAmount() + "đ");

        StringBuilder seats = new StringBuilder();
        for (Seat seat : gioHang.getSelectedSeats()) {
            seats.append(seat.getSeatNumber()).append(" ");
        }
        holder.selectedSeatsTxt.setText(seats.toString().trim());
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {
        TextView movieNameTxt, totalAmountTxt, selectedSeatsTxt;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            movieNameTxt = itemView.findViewById(R.id.item_giohang_tensp);
            totalAmountTxt = itemView.findViewById(R.id.txttongtien);
            selectedSeatsTxt = itemView.findViewById(R.id.item_giohang_soluong);
        }
    }
}
