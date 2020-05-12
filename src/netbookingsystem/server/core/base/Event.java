package netbookingsystem.server.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class Event  implements Serializable {

    private String title;
    private String type;
    private EventType eventType;
    private ArrayList<Show> shows;
    private String id;

    public Event (String title, String type, ArrayList<Show> shows) {
        this.title = title;
        this.type = type;
        this.eventType = EventType.valueOf(type);
        this.shows = shows;
        this.id = UUID.randomUUID().toString();
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

    public String getId() { return id; }

    @Override
    public String toString(){
        String s = getTitle()+"  |  "+"Σε "+shows.size()+" Παραστάσεις";
        return s;
    }

}

