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



    public synchronized ArrayList<Event> getEventsFromDB () throws IOException, ClassNotFoundException {
       ArrayList<String> params = new ArrayList<>();
       params.add("GET");
       params.add("EVENTS");
       Protocol protocol = new Protocol(params);
       dbSocket.getOut().writeObject(protocol);
       Protocol response = (Protocol) dbSocket.getIn().readObject();
       return response.getEvents();
    }


    public synchronized ArrayList<Ticket> getTicketsFromDB () throws IOException, ClassNotFoundException {
        ArrayList<String> params = new ArrayList<>();
        params.add("GET");
        params.add("TICKETS");
        Protocol protocol = new Protocol(params);
        dbSocket.getOut().writeObject(protocol);
        Protocol response = (Protocol) dbSocket.getIn().readObject();
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

   public void addTicket(){

   }

   public void deleteTicket(){}
}
