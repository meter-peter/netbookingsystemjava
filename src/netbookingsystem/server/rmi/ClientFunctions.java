package netbookingsystem.server.rmi;


import netbookingsystem.ClientInterface;


import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.Controller;
import netbookingsystem.server.core.base.Event;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientFunctions implements ClientInterface {
    AuthService authService;
    Controller controller;

    public ClientFunctions(AuthService authService , Controller controller) throws RemoteException {
        super();
        this.authService = authService;
        this.controller=controller;

    }

    public AuthStatus login(String username, String password) throws Exception {
       return authService.loginAccount(username, password);


    }
    public AuthStatus register(String username , String password , String email , String firstname, String lastname) throws Exception {
        return authService.createAccount(username, password, email, firstname, lastname);
    }

    public ArrayList<Event> getAvailableEvents() {
        return controller.getEvents();

    }

}
