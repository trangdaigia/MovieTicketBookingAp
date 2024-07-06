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
import com.example.movieticketbookingap.R;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    private List<GioHang> gioHangList;
    private Context context;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
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
        // Hiển thị thông tin sản phẩm trong giỏ hàng
        holder.item_giohang_tensp.setText(gioHang.getTenSanPham());
        holder.item_giohang_gia.setText(String.valueOf(gioHang.getGia()));
        holder.item_giohang_soluong.setText(String.valueOf(gioHang.getSoLuong()));
        Glide.with(context)
                .load(gioHang.getPic())
                .into(holder.item_giohang_image);
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong;
        ImageView item_giohang_image;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
        }
    }

    /*
    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new GioHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        GioHang giohang = items.get(position);
        holder.item_giohang_tensp.setText(giohang.getTenSp());
        Glide.with(context)
                .load(giohang.getPicSp())
                .into(holder.item_giohang_image);
        holder.item_giohang_tensp.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("giohang",items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_giohang_tensp;
        ImageView item_giohang_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
        }
    }*/
}
