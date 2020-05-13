package netbookingsystem;

import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

    public AuthStatus login(String username, String password , UserInterface clientInterface) throws Exception;
    public AuthStatus register(String username, String password , String email , String firstname, String lastname, UserInterface clientInterface) throws Exception;
    public ArrayList<Event> getAvailableEvents() throws RemoteException;
    public void book(String username , Event event, Show show , int seats) throws Exception;
    public void addEvent(Event event) throws Exception;
}
