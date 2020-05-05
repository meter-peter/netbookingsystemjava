package netbookingsystem.server.core.base;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String ticketHolder;
    private int seats;
    private double ticketPrice;
    private double priceSum;

    public Ticket (String ticketHolder, int seats, double ticketPrice) {
        this.ticketHolder = ticketHolder;
        this.seats = seats;
        this.ticketPrice = ticketPrice;
    }
    //polaa akoma sum kai ta sxetika
}
