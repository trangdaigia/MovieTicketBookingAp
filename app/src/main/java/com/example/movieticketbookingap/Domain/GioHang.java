package com.example.movieticketbookingap.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class GioHang implements Serializable {
    private String movieName;
    private int totalAmount;
    private ArrayList<Seat> selectedSeats;
    private String bapNuoc;
    private int soLuong;
    

    public GioHang() {
        // Default constructor required for calls to DataSnapshot.getValue(GioHang.class)
    }

    public GioHang(String movieName, int totalAmount, ArrayList<Seat> selectedSeats, String bapNuoc, int soLuong) {
        this.movieName = movieName;
        this.totalAmount = totalAmount;
        this.selectedSeats = selectedSeats;
        this.bapNuoc = bapNuoc;
        this.soLuong = soLuong;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ArrayList<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(ArrayList<Seat> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public String getBapNuoc() {
        return bapNuoc;
    }

    public void setBapNuoc(String bapNuoc) {
        this.bapNuoc = bapNuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
