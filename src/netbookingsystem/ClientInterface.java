package netbookingsystem;

import netbookingsystem.server.auth.AuthStatus;

import java.rmi.Remote;

public interface ClientInterface extends Remote {

    public AuthStatus login(String username, String password) throws Exception;
    public AuthStatus register(String username, String password , String email , String firstname, String lastname) throws Exception;



}
