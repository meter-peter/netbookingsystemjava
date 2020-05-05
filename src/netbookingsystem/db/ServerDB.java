package netbookingsystem.db;

import netbookingsystem.server.core.Ticket;
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
                switch (action) {
                    case READ: //read operation from database
                        switch (type) { //type of read operation
                            case EVENT:
                                ArrayList<Event> events = serverDB.readEvents(); //read events
                                Protocol responsePacketI = new Protocol(action, type, newStatus, events);
                                serverDB.getOut().writeObject(responsePacketI);
                            case TICKET:
                                ArrayList<Ticket> tickets = serverDB.readTickets();
                                Protocol responsePacketII = new Protocol(action, type, newStatus, tickets);
                                serverDB.getOut().writeObject(responsePacketII);
                        }
                    case WRITE:
                        switch (type) {
                            case EVENT:
                                if (serverDB.writeEvent(protocol.getEvent())) {
                                    Protocol responsePacketIII = new Protocol(action, type, newStatus);
                                    serverDB.getOut().writeObject(responsePacketIII);
                                }
                            case TICKET:
                                if (serverDB.writeTicket(protocol.getTicket())) {
                                    Protocol responseProtocolIV = new Protocol(action, type, newStatus);
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
    public boolean writeEvent (Event event) throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToEventsFile);
        if (fileIO.writeEventToFile(event)) {
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
    public boolean writeTicket (Ticket ticket) throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToTicketsFile);
        if (fileIO.writeTicketToFile(ticket)) {
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