package netbookingsystem.server.core.base;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Show implements Serializable {

    private Date dayStart;
    private int availSeats;
    private double ticketPrice;
    private String id;

    public Show (Date dayStart, int availSeats, double ticketPrice) {
        this.dayStart = dayStart;
        this.availSeats = availSeats;
        this.ticketPrice = ticketPrice;
        this.id = UUID.randomUUID().toString();
    }

    public double bookseats(int numnber){
        this.availSeats = this.availSeats -numnber;
        return numnber*ticketPrice;
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

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
