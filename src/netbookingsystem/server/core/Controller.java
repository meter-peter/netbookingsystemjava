package netbookingsystem.server.core;

import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.User;
import netbookingsystem.server.rmi.RemoteFunctions;

public class Controller {

    public Controller(){
        AuthService authService = new AuthService(this);
        RemoteFunctions remoteFunctions = new RemoteFunctions(authService);



    }


    public void continuewithlogin(User user){

    }
}
