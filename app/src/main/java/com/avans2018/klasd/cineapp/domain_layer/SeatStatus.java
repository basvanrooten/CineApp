package com.avans2018.klasd.cineapp.domain_layer;

/**
 * Created by Gebruiker on 3-4-2018.
 */

public enum SeatStatus {

    FREE(0), IS_RESERVATED(1), IS_TAKEN(2);

    private int status;

    SeatStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static SeatStatus getSeatByStatus(int status){
        switch (status){
            case 0: return FREE;
            case 1: return IS_RESERVATED;
            case 2: return IS_TAKEN;
            default: return FREE;
        }
    }


}
