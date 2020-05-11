package netbookingsystem.server.core;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.DBSocket;
import netbookingsystem.server.netdriver.Protocol;

import java.io.IOException;
import java.util.ArrayList;

public class DBFunctions {
    DBSocket dbSocket;
    public DBFunctions(DBSocket dbSocket){
        this.dbSocket=dbSocket;
    }



    public  ArrayList<Event> getEventsFromDB () throws IOException, ClassNotFoundException {
       ArrayList<String> params = new ArrayList<>();
       params.add("GET");
       params.add("EVENTS");
       Protocol protocol = new Protocol(params);
       dbSocket.getOut().writeObject(protocol);
       Protocol response = (Protocol) dbSocket.getIn().readObject();
       return response.getEvents();
    }




    public ArrayList<Ticket> getTicketsFromDB () throws IOException, ClassNotFoundException {
        ArrayList<String> params = new ArrayList<>();
        params.add("GET");
        params.add("TICKETS");
        Protocol protocol = new Protocol(params);
        dbSocket.getOut().writeObject(protocol);
        Protocol response = (Protocol) dbSocket.getIn().readObject();
        System.out.println("DBFUNCTIONS"+response.getTickets());
        return response.getTickets();
    }

   public synchronized void addEvent(Event event){
        ArrayList<String> params = new ArrayList<>();
        params.add("ADD");
        params.add("EVENT");
        Protocol protocol = new Protocol(params);
        protocol.setToSendEvent(event);


   }

   public void deleteEvent(){

   }



   public void addTicket(Ticket ticket) throws IOException {
        ArrayList<String> params = new ArrayList<>();
        params.add("ADD");
        params.add("TICKET");
        Protocol protocol = new Protocol(params);
        protocol.setToSendTicket(ticket);
        dbSocket.getOut().writeObject(protocol);

   }

   public void deleteTicket(){}
}
