package netbookingsystem.db;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.Action;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.netdriver.Protocol;
import netbookingsystem.server.netdriver.Status;
import netbookingsystem.server.netdriver.Type;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerDB {

    private ServerSocket serverSocket;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream out;
    private InputStream inputStream;
    private ObjectInputStream in;
    private static final String pathToEventsFile = "src/netbookingsystem/db/events.dat";
    private static final String pathToTicketsFile = "src/netbookingsystem/db/tickets.dat";

    public ServerDB () throws IOException {
        this.serverSocket = new ServerSocket(5555);
    }

    public static void main (String args[]) throws IOException, ClassNotFoundException {
        //init serverDB object
        ServerDB serverDB = new ServerDB();

        //wait for incoming connection
        while (true) {
            //open socket with client
            Socket socket = serverDB.getServerSocket().accept();
            System.out.println("Client connected!");
            serverDB.setSocket(socket);
            //after client's connection -> init Streams
            serverDB.initStreams();
            //get income packet
            Object ob = serverDB.getIn().readObject();
            System.out.println("pernaei!");
            if (ob instanceof Protocol) {
                Protocol protocol = (Protocol) ob;
                Action action = protocol.getAction();
                Type type = protocol.getType();
                System.out.println(action);
                //read write action
                Status newStatus = Status.POST; //status from serverDB
                Protocol response = new Protocol();

                switch (action) {
                    case READ: //read operation from database
                        switch (type) { //type of read operation
                            case EVENT:
                                ArrayList<Event> events = serverDB.readEvents(); //read events
                                response.setEvents(events);
                                response.setAction(action);
                                response.setType(type);
                                response.setStatus(newStatus);
                                serverDB.getOut().writeObject(response);
                            case TICKET:
                                ArrayList<Ticket> tickets = serverDB.readTickets();
                                response.setTickets(tickets);
                                response.setAction(action);
                                response.setType(type);
                                response.setStatus(newStatus);
                                serverDB.getOut().writeObject(response);
                        }
                    case WRITE:
                        switch (type) {
                            case EVENT:
                                System.out.println("Event");
                                if (serverDB.writeEvents(protocol.getEvents())) {
                                    response.setAction(action);
                                    response.setType(type);
                                    response.setStatus(newStatus);
                                    serverDB.getOut().writeObject(response);
                                }
                            case TICKET:
                                System.out.println("Ticket");
                                if (serverDB.writeTickets(protocol.getTickets())) {
                                    response.setAction(action);
                                    response.setType(type);
                                    response.setStatus(newStatus);
                                    serverDB.getOut().writeObject(response);
                                }
                        }
                }
            }
            serverDB.closeConnention();
        }

    }

    public void closeConnention () throws IOException {
        getSocket().close();
    }

    //return events
    public ArrayList<Event> readEvents () throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToEventsFile);
        ArrayList<Event> events = fileIO.readEventsFromFile();
        return events;
    }
    //write new event
    public boolean writeEvents (ArrayList<Event> events) throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToEventsFile);
        if (fileIO.writeEventsToFile(events)) {
            return true;
        }
        else {
            return false;
        }
    }

    //return tickets
    public ArrayList<Ticket> readTickets () throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToTicketsFile);
        ArrayList<Ticket> tickets = fileIO.readTicketsFromFile();
        return tickets;
    }
    //write new ticket
    public boolean writeTickets (ArrayList<Ticket> tickets) throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToTicketsFile);
        if (fileIO.writeTicketsToFile(tickets)) {
            return true;
        }
        else {
            return false;
        }
    }


    public void initStreams () throws IOException {
        this.outputStream = this.socket.getOutputStream();
        this.out = new ObjectOutputStream(this.outputStream);
        this.inputStream = this.socket.getInputStream();
        this.in = new ObjectInputStream(this.inputStream);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
}
