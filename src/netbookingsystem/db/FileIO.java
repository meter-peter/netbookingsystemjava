package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Ticket;

import java.io.*;
import java.util.ArrayList;

public class FileIO {

    private String pathToFile;
    private File file;

    public FileIO (String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        this.file = new File(this.pathToFile);
    }

    public boolean writeEventsToFile (ArrayList<Event> events) throws IOException, ClassNotFoundException {
        try {
            if (!file.exists()) {
                ArrayList<Event> eventsToConcet = readEventsFromFile();//debug
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
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
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return events;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            events = (ArrayList<Event>) in.readObject();
            return events;
        }
    }

    public boolean writeTicketsToFile (ArrayList<Ticket> tickets) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
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
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tickets;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            tickets = (ArrayList<Ticket>) in.readObject();
            return tickets;
        }
    }

}
