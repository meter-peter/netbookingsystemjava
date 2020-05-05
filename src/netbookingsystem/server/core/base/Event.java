package netbookingsystem.server.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Event  implements Serializable {

    private String title;
    private String type;
    private EventType eventType;
    private ArrayList<Show> shows;

    public Event (String title, String type, ArrayList<Show> shows) {
        this.title = title;
        this.type = type;
        this.eventType = EventType.valueOf(type);
        this.shows = shows;
    }

    public static void main (String args[]) {
        Date date = new Date();
        Show show = new Show(date, 100, 10.05);
        ArrayList<Show> shows = new ArrayList<>();
        shows.add(show);
        Event event = new Event("Damian marley live in Athens", "CONCERT", shows);
        System.out.println(event.getEventType().type);
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public EventType getEventType() {
        return eventType;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }
}

