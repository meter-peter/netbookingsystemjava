package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Ticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Protocol implements Serializable {
    ArrayList<String> params;
    ArrayList<Ticket> tickets;
    ArrayList<Event> events;
    Event event;
    Ticket ticket;

    //ADD TICKET
    //DELETE TICKET
    //UPDATE TICKET
    //GET TICKET(S)

    //TO IDIO ME TA SHOWS

    public Protocol(ArrayList<String> params) {
        this.params = params;
    }

    public void setToSendevents(ArrayList events) {
        this.events = events;
    }

    public void setToSendTickets(ArrayList<Ticket> arrayList) {
        this.tickets = arrayList;
    }

    public void setToSendTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setToSendEvent(Event event) {
        this.event = event;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Event getEvent() {
        return event;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
