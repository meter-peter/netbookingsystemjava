package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.Event;
import netbookingsystem.server.core.Ticket;

import java.io.Serializable;
import java.util.ArrayList;

public class Protocol implements Serializable {

    private ArrayList<Event> events;
    private Event event;
    private ArrayList<Ticket> tickets;
    private Ticket ticket;
    private Action action;
    private Type type;
    private Status status;


    //write event
    public Protocol (Action action, Type type, Status status, Event event) {
        this.action = action;
        this.type = type;
        this.event = event;
    }
    //read events
    public Protocol (Action action, Type type, Status status, ArrayList<Event> events) {
        this.action = action;
        this.type = type;
        this.events = events;
    }
    //read tickets
    public Protocol (Action action, Type type, Status status, ArrayList<Ticket> tickets) {
        this.action = action;
        this.type = type;
        this.tickets = tickets;
    }
    //write ticket
    public Protocol (Action action, Type type, Status status, Ticket ticket) {
        this.action = action;
        this.type = type;
        this.ticket = ticket;
    }
    //for write operation response
    public Protocol (Action action, Type type, Status status) {
        this.action = action;
        this.type = type;
        this.status = status;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
