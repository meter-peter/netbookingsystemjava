package netbookingsystem.server.core;

import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.User;
import netbookingsystem.server.rmi.ClientFunctions;
import netbookingsystem.server.rmi.RmiDriver;

import java.io.IOException;

public class Controller {

    public Controller() throws IOException {
        AuthService authService = new AuthService(this);
        ClientFunctions remoteFunctions = new ClientFunctions(authService);
        RmiDriver rmiDriver = new RmiDriver(remoteFunctions);



    }


    public void continuewithlogin(User user){

    }
}
