package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.Action;
import netbookingsystem.server.netdriver.Protocol;
import netbookingsystem.server.netdriver.Status;
import netbookingsystem.server.netdriver.Type;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Main{

    public static void main (String args[]) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("localhost", 5555);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        ObjectInputStream in = new ObjectInputStream(inputStream);

        Date date = new Date();
        Show show = new Show(date, 100, 10.05);
        ArrayList<Show> shows = new ArrayList<>();
        shows.add(show);
        Event event = new Event("Damian marley live in Athens", "CONCERT", shows);

        Ticket ticket = new Ticket("NikosRekkas", 3, event.getTitle(), event.getType(), show);

        Protocol packetToSend = new Protocol();
        packetToSend.setAction(Action.WRITE);
        packetToSend.setType(Type.TICKET);
        packetToSend.setStatus(Status.GET);
        packetToSend.setTicket(ticket);

        out.writeObject(packetToSend);

        Protocol packetFromServer = (Protocol) in.readObject();

    }

}
