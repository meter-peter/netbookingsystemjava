package netbookingsystem.server.core;

import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.User;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.DBSocket;
import netbookingsystem.server.rmi.ClientFunctions;
import netbookingsystem.server.rmi.RmiDriver;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    DBSocket dbSocket;
    DBFunctions dbFunctions;
    AuthService authService;
    ClientFunctions remoteFunctions;
    ArrayList<Event> liveEvents;
    ArrayList<Ticket> liveTickets;
    RmiDriver rmiDriver;

    public Controller() throws IOException {
        dbSocket = new DBSocket();
        dbFunctions = new DBFunctions(dbSocket);
        authService = new AuthService(this);
        remoteFunctions = new ClientFunctions(authService);
        rmiDriver = new RmiDriver(remoteFunctions);

    }

    public synchronized void syncData() throws IOException, ClassNotFoundException {
        liveEvents=dbFunctions.getEventsFromDB();
        liveTickets=dbFunctions.getTicketsFromDB();


    }


}
