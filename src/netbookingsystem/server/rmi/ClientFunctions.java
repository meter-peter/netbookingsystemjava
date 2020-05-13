package netbookingsystem.server.rmi;


import netbookingsystem.ClientInterface;


import netbookingsystem.UserInterface;
import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.ServerController;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

public class ClientFunctions implements ClientInterface {
    AuthService authService;
    ServerController controller;


    public ClientFunctions(AuthService authService , ServerController controller) throws RemoteException {
        super();
        this.authService = authService;
        this.controller=controller;

    }

    public AuthStatus login(String username, String password , UserInterface clientInterface) throws Exception {
        AuthStatus authStatus = authService.loginAccount(username, password);
        if(authStatus==AuthStatus.SUCCESS)
            controller.join(clientInterface);
       return authStatus;


    }
    public AuthStatus register(String username , String password , String email , String firstname, String lastname,UserInterface clientInterface) throws Exception {
        AuthStatus authStatus = authService.loginAccount(username, password);
        if(authStatus==AuthStatus.SUCCESS)
            controller.join(clientInterface);
        return authService.createAccount(username, password, email, firstname, lastname);
    }

    public ArrayList<Event> getAvailableEvents() {
        return controller.getEvents();

    }

    public void book(String username , Event event, Show show , int seats) throws IOException, ParseException {
        controller.book(username, event, show, seats);
    }

    public void addEvent(Event event) throws Exception{
        controller.addEvent(event);

    }
}
