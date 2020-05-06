package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Ticket;

import java.io.Serializable;
import java.util.ArrayList;

public class Protocol implements Serializable {

    private ArrayList<Event> events;
    private ArrayList<Ticket> tickets;
    private Action action;
    private Type type;
    private Status status;


    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
