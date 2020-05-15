package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;

import java.io.*;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class FileIO {

    private static final String eventsfile ="databaseFiles/events.dat";
    private static final String ticketsfile ="databaseFiles/tickets.dat";
    private File fileevents;
    private File filetickets;


    public FileIO() throws IOException {
      fileevents = new File(eventsfile);
      filetickets = new File(ticketsfile);
    }


    public boolean writeEventsToFile (ArrayList<Event> events) throws IOException, ClassNotFoundException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileevents));
            out.writeObject(events);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeTicketsToFile (ArrayList<Ticket> tickets) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filetickets));
        out.writeObject(tickets);
        out.flush();
        out.close();
        return true;
    }

    public ArrayList<Ticket> readTicketsFromFile () throws IOException, ClassNotFoundException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        if (!filetickets.exists()) {
            filetickets.getParentFile().mkdirs();
            System.out.println("AOYA");
            return tickets;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filetickets));
            tickets = (ArrayList<Ticket>) in.readObject();
            if(tickets ==null)
                return new ArrayList<>();
            else
                return tickets;
        }
    }


    public ArrayList<Event> readEventsFromFile () throws IOException, ClassNotFoundException {
        ArrayList<Event> events = new ArrayList<>();
        if (!fileevents.exists()) {
            fileevents.getParentFile().mkdirs();
            return events;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileevents));
            events = (ArrayList<Event>) in.readObject();
            if (events == null) {
                return new ArrayList<>();
            } else {
                return events;
            }
        }
    }

}
