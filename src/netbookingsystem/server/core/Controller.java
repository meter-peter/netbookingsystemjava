package netbookingsystem.server.core;

import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.User;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
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


    public Controller() throws IOException, ClassNotFoundException {
        liveTickets=new ArrayList<>();
        liveEvents=new ArrayList<>();
        dbSocket = new DBSocket();
        dbFunctions = new DBFunctions(dbSocket);
        authService = new AuthService(this);
        remoteFunctions = new ClientFunctions(authService ,this);
        rmiDriver = new RmiDriver(remoteFunctions);
        syncData();
    }

    public  void syncData() throws IOException, ClassNotFoundException {
        liveEvents.clear();
        liveEvents.addAll(dbFunctions.getEventsFromDB());
        liveTickets.clear();
        liveTickets = dbFunctions.getTicketsFromDB();
        System.out.println(liveTickets);
    }

    public synchronized ArrayList<Ticket> getLiveTickets(){
        return liveTickets;
    }
    public synchronized ArrayList<Event> getEvents(){
        return liveEvents;
    }


    public void addEvent(Event event){
        liveEvents.add(event);
        dbFunctions.addEvent(event);
    }



    public double book(String userid , Event event , Show show,int seats) throws IOException {
    System.out.println(userid+event.getTitle()+show.getId()+seats);

        for (int i = 0; i < liveEvents.size(); i++) {
            if (event.getId().equals(liveEvents.get(i).getId())) {
                System.out.println(liveEvents.get(i));
                for (int j = 0; j < liveEvents.get(i).getShows().size(); j++) {

                    if (show.getId().equals(liveEvents.get(i).getShows().get(j).getId())) {
                        System.out.println("AOUA");
                        Ticket ticket = new Ticket(userid, seats, event.getTitle(), show);
                        ticket.setPriceSum(liveEvents.get(i).getShows().get(j).bookseats(seats));
                        System.out.println(ticket);
                        getLiveTickets().add(ticket);
                        dbFunctions.addTicket(ticket);

                        return ticket.getPriceSum();
                    }
                }

            }

        }

        return 0.0;
    }
    }