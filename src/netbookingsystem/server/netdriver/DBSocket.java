package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class DBSocket {

    public DBSocket() throws IOException, ClassNotFoundException {
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

        Protocol packetToSend = new Protocol();
        packetToSend.setAction(Action.WRITE);
        packetToSend.setType(Type.TICKET);
        packetToSend.setStatus(Status.GET);

        out.writeObject(packetToSend);

        Protocol packetFromServer = (Protocol) in.readObject();


    }{

    }

}
