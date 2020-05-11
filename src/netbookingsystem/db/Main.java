package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Main{

    public static void main (String args[]) throws IOException, ClassNotFoundException {

        Date date = new Date();
        Show show = new Show(date, 100, 10.05);
        ArrayList<Show> shows = new ArrayList<>();
        shows.add(show);
        Event event = new Event("Damian marley live in Athens", "CONCERT", shows);

//        Ticket ticket = new Ticket("NikosRekkas", 3, event.getTitle(), event.getType(), show);
        ArrayList<Event> events = new ArrayList<>();
        events.add(event);

    }

}
