package com.example.movieticketbookingap.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class GioHang {
    private String movieName;
    private int totalAmount;
    private ArrayList<Seat> selectedSeats;

    // Constructors, getters, and setters
    public GioHang() {}

    public GioHang(String movieName, int totalAmount, ArrayList<Seat> selectedSeats) {
        this.movieName = movieName;
        this.totalAmount = totalAmount;
        this.selectedSeats = selectedSeats;
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
}
