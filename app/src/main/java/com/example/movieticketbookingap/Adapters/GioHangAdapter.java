package com.example.movieticketbookingap.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketbookingap.Domain.GioHang;
import com.example.movieticketbookingap.Domain.Seat;
import com.example.movieticketbookingap.R;

import java.util.ArrayList;

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
        holder.itemGioHangTenSP.setText(gioHang.getMovieName());
        holder.itemGioHangGia.setText(String.valueOf(gioHang.getTotalAmount()));
        holder.itemGioHangSoLuong.setText(String.valueOf(gioHang.getSelectedSeats().size()));
        holder.itemGioHangBapNuoc.setText("Bắp nước: " + gioHang.getBapNuoc());
        holder.itemGioHangSoLuong.setText("Số lượng: " + gioHang.getSoLuong());

        // Set image (if you have an image to set)
        // holder.itemGioHangImage.setImageResource(...); // Set your image resource here
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {
        ImageView itemGioHangImage;
        TextView itemGioHangTenSP, itemGioHangGia, itemGioHangSoLuong, itemGioHangBapNuoc;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            itemGioHangImage = itemView.findViewById(R.id.item_giohang_image);
            itemGioHangTenSP = itemView.findViewById(R.id.item_giohang_tensp);
            itemGioHangGia = itemView.findViewById(R.id.item_giohang_gia);
            itemGioHangSoLuong = itemView.findViewById(R.id.item_giohang_soluong2);
            itemGioHangBapNuoc = itemView.findViewById(R.id.item_giohang_bapnuoc);
        }
    }
}
