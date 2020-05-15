package netbookingsystem;

import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

    public AuthStatus login(String username, String password , UserInterface clientInterface) throws Exception;
    public AuthStatus register(String username, String password , String email , String firstname, String lastname, UserInterface clientInterface) throws Exception;
    public ArrayList<Event> getAvailableEvents() throws RemoteException;
    public boolean book(String username , Event event, Show show , int seats) throws Exception;
    public void addEvent(Event event) throws Exception;
    public ArrayList<Ticket> getUserTickets(String username) throws Exception;
    public void deleteTicket(Ticket ticket) throws Exception;
    public void deleteEvent(String event) throws Exception;
    public void deleteAccount(String username) throws Exception;
}
