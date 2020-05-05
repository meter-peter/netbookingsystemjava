package netbookingsystem.db;

import netbookingsystem.server.netdriver.Action;
import netbookingsystem.server.core.Event;
import netbookingsystem.server.netdriver.Protocol;
import netbookingsystem.server.core.Show;

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

        Protocol packetToSend = new Protocol(Action.WRITE, event);

        out.writeObject(packetToSend);

        Protocol packetFromServer = (Protocol) in.readObject();

        System.out.println(packetFromServer.getAction()+" "+packetFromServer.isStatus());

    }
    //write users to file
    public static void writeToFile(Event event) throws IOException, ClassNotFoundException {
        ArrayList<Event> users = readFromFile();
        users.add(event);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream( new File("src/netbookingsystem/server/db/events.dat")));
        out.writeObject(users);
        out.flush();
        out.close();
    }

    //read users from file
    public static ArrayList<Event> readFromFile() throws IOException, ClassNotFoundException {
        ArrayList<Event> users = new ArrayList<>();
        File f = new File("src/netbookingsystem/server/db/events.dat");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            return users;
        } else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            users = (ArrayList<Event>) in.readObject();
            return users;
        }
    }



}
