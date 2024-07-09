package com.example.movieticketbookingap.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class GioHang implements Serializable {
    private String movieName;
    private int totalAmount;
    private ArrayList<Seat> selectedSeats;
    private int popcornCount;
    private int drinkCount;
    private String image;

    public GioHang() {
    }

    public GioHang(String movieName, int totalAmount, ArrayList<Seat> selectedSeats, int popcornCount, int drinkCount) {
        this.movieName = movieName;
        this.totalAmount = totalAmount;
        this.selectedSeats = selectedSeats;
        this.popcornCount = popcornCount;
        this.drinkCount = drinkCount;
        this.image = image;
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

    public int getPopcornCount() {
        return popcornCount;
    }

    public void setPopcornCount(int popcornCount) {
        this.popcornCount = popcornCount;
    }

    public int getDrinkCount() {
        return drinkCount;
    }

    public void setDrinkCount(int drinkCount) {
        this.drinkCount = drinkCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
