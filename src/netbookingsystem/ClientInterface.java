package netbookingsystem;

import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

    public AuthStatus login(String username, String password) throws Exception;
    public AuthStatus register(String username, String password , String email , String firstname, String lastname) throws Exception;
    public ArrayList<Event> getAvailableEvents() throws RemoteException;


}
