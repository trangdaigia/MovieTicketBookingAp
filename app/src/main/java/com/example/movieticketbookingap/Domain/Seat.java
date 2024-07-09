package com.example.movieticketbookingap.Domain;

import java.io.Serializable;

public class Seat implements Serializable {
    private String seatNumber;

    public Seat() {
    }
    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
