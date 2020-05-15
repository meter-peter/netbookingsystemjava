package netbookingsystem.server.core;

import netbookingsystem.UserInterface;
import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.DBSocket;
import netbookingsystem.server.rmi.ClientFunctions;
import netbookingsystem.server.rmi.RmiDriver;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class ServerController {
    DBSocket dbSocket;
    DBFunctions dbFunctions;
    AuthService authService;
    ClientFunctions remoteFunctions;
    ArrayList<Event> liveEvents;
    ArrayList<Ticket> liveTickets;
    RmiDriver rmiDriver;
    ArrayList<UserInterface> loggedUsers;
    Calendar calendar ;
    int dayOfWeek;




    public ServerController() throws IOException, ClassNotFoundException {
        liveTickets=new ArrayList<>();
        liveEvents=new ArrayList<>();
        dbSocket = new DBSocket();
        dbFunctions = new DBFunctions(dbSocket);
        authService = new AuthService(this);
        remoteFunctions = new ClientFunctions(authService ,this);
        rmiDriver = new RmiDriver(remoteFunctions);
        loggedUsers = new ArrayList<>();
        calendar = Calendar.getInstance(TimeZone.getDefault());

        syncData();
    }

    public void join(UserInterface userInterface){
        loggedUsers.add(userInterface);
    }



    public void showDiscountMessage(String id) throws Exception {
        for(UserInterface user : loggedUsers){
            user.pushNotification("WELCOME");


        }
    }
    public synchronized void syncData() throws IOException, ClassNotFoundException {
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        liveEvents = dbFunctions.getEventsFromDB();
        liveTickets = dbFunctions.getTicketsFromDB();
        System.out.println("SYNCDATA TICKETS"+liveTickets);
        System.out.println("SYNCDATA EVENTS"+liveEvents);

    }

    public  ArrayList<Ticket> getLiveTickets(){
        return liveTickets;
    }
    public  ArrayList<Event> getEvents(){
        return liveEvents;
    }


    public synchronized void addEvent(Event event) throws IOException, ClassNotFoundException {
        syncData();
        liveEvents.add(event);
        dbFunctions.addEvent(event);
    }


    public synchronized ArrayList<Ticket> getUserTickets(String username) throws IOException, ClassNotFoundException {
        syncData();
        ArrayList<Ticket> temp = new ArrayList<>();
        if(liveTickets!=null) {
            for (Ticket ticket : liveTickets) {
                if ((ticket.getTicketHolder().equals(username)))
                    temp.add(ticket);
            }
            return temp;
        }else return new ArrayList<>();
    }

    public synchronized void  deleteTicket(Ticket ticket) throws IOException, ClassNotFoundException {
        syncData();
        for(int i=0;i<liveTickets.size();i++){
            if(ticket.getId().equals(liveTickets.get(i).getId()));
            liveTickets.remove(i);
            dbFunctions.deleteTicket(ticket);



        }

    }

    public synchronized boolean book(String userid , Event event , Show show, int seats) throws Exception {
        System.out.println(userid+event.getTitle()+show.getId()+seats);
        for (int i = 0; i < liveEvents.size(); i++) {
            if (event.getId().equals(liveEvents.get(i).getId())) {
                for (int j = 0; j < liveEvents.get(i).getShows().size(); j++) {

                    Show temp =liveEvents.get(i).getShows().get(j);

                    if (show.getId().equals(temp.getId())&&temp.getAvailSeats()>=seats) {
                        Ticket ticket = new Ticket(userid, seats, event.getTitle(), temp);
                        ticket.setPriceSum(liveEvents.get(i).getShows().get(j).bookseats(seats));
                        onBook(liveEvents.get(i).getId(),ticket,temp,event);
                        return true;
                    }
                    else return false;
                }

            }

        }

        return false;
    }

    public void deleteAccount(String username){
        authService.deleteAccount(username);

    }

    public void deleteEvent(String eventid) throws IOException, ClassNotFoundException {
        dbFunctions.deleteEvent(eventid);
        liveEvents=dbFunctions.getEventsFromDB();

    }
    public synchronized void onBook(String id,Ticket ticket , Show show, Event event) throws Exception {
        getLiveTickets().add(ticket);
        dbFunctions.addTicket(ticket);
        System.out.println("Ticket Added");

        dbFunctions.modifyShow(id,show.getId(),show.getAvailSeats(),show.getTicketPrice());
        if(show.getAvailSeats()<=10){
            Double newprice = (show.getTicketPrice()) - (show.getTicketPrice()*40/100);
            dbFunctions.modifyShow(id,show.getId(),show.getAvailSeats(),newprice);
            showDiscountMessage("10 θέσεις έμειναν , Εκπτωση 40% στο "+event.getTitle()+" στην παράσταση "+show.getDayStart());

        }


    }

}