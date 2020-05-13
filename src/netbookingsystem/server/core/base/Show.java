package netbookingsystem.server.core.base;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Show implements Serializable {

    private Date dayStart;
    private String timestart;
    private int availSeats;
    private double ticketPrice;
    private String id;
    String place;

    public Show (Date dayStart,String time, int availSeats, double ticketPrice,String place) {
        this.place=place;
        this.timestart=time;
        this.dayStart = dayStart;
        this.availSeats = availSeats;
        this.ticketPrice = ticketPrice;
        this.id = UUID.randomUUID().toString();
    }

    public double bookseats(int numnber){
        this.availSeats = this.availSeats-numnber;
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

    public String getTime(){
        return timestart;
    }

    public String getPlace(){
        return place;
    }

    @Override
    public String toString(){
        String s=getDayStart()+"-"+getTime()+"@"+ getPlace()+" :: " + getTicketPrice()+"€";
        return s;
    }
}
