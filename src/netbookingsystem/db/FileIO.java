package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Ticket;

import java.io.*;
import java.util.ArrayList;

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

    public ArrayList<Event> readEventsFromFile () throws IOException, ClassNotFoundException {
        ArrayList<Event> events = new ArrayList<>();
        if (!fileevents.exists()) {
            fileevents.getParentFile().mkdirs();
            return events;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileevents));
            events = (ArrayList<Event>) in.readObject();
            return events;
        }
    }

    public boolean writeTicketsToFile (ArrayList<Ticket> tickets) {
        try {
            if (!filetickets.exists()) {
                filetickets.getParentFile().mkdirs();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filetickets));
            out.writeObject(tickets);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Ticket> readTicketsFromFile () throws IOException, ClassNotFoundException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        if (!filetickets.exists()) {
            filetickets.getParentFile().mkdirs();
            return tickets;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filetickets));
            tickets = (ArrayList<Ticket>) in.readObject();
            return tickets;
        }
    }

}
