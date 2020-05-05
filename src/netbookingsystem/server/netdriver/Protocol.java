package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.base.Event;

import java.io.Serializable;
import java.util.ArrayList;

public class Protocol implements Serializable {

    private ArrayList<Event> events;
    private Event event;
    private Action action;
    private boolean status;

    public Protocol (Action action, ArrayList<Event> events) {
        this.action = action;
        this.events = events;
    }

    public Protocol (Action action, boolean status) {
        this.action = action;
        this.status = status;
    }

    public Protocol (Action action, Event event) {
        this.action = action;
        this.event = event;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
