package netbookingsystem.server.core;

import java.io.Serializable;
import java.util.Date;

public class Show implements Serializable {

    private Date dayStart;
    private int availSeats;
    private double ticketPrice;

    public Show (Date dayStart, int availSeats, double ticketPrice) {
        this.dayStart = dayStart;
        this.availSeats = availSeats;
        this.ticketPrice = ticketPrice;
    }

    public Date getDayStart() {
        return dayStart;
    }

    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }

    public int getAvailSeats() {
        return availSeats;
    }

    public void setAvailSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
