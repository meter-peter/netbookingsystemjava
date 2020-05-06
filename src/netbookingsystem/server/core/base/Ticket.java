package netbookingsystem.server.core.base;

import java.io.Serializable;
import java.util.UUID;

public class Ticket implements Serializable {

    private String ticketHolder;
    private int seats;
    private String title;
    private String type;
    private Show show;
    private double priceSum;
    private String id;

    public Ticket (String ticketHolder, int seats, String title, String type, Show show) {
        this.ticketHolder = ticketHolder;
        this.seats = seats;
        this.title = title;
        this.type = type;
        this.show = show;
        this.id = UUID.randomUUID().toString();
    }

    public double getTicketPrice () {
        return seats*show.getTicketPrice();
    }

    public String getTicketHolder() {
        return ticketHolder;
    }

    public void setTicketHolder(String ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(double priceSum) {
        this.priceSum = priceSum;
    }

    public String getId() { return id; }
}
