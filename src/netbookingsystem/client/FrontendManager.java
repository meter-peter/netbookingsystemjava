package netbookingsystem.client;

import netbookingsystem.client.GUI.LoginRegister;
import netbookingsystem.client.RMI;
import netbookingsystem.server.auth.AuthStatus;

public class FrontendManager {
    RMI rmi;

    public FrontendManager(RMI rmi){
        this.rmi=rmi;
        new LoginRegister(this);


    }


    public AuthStatus login(String username, String password) throws Exception {
       return rmi.login(username, password);
    }

    public  AuthStatus register(String username, String password,String email , String firstname , String lastname) throws Exception{
        return rmi.register(username, password, email, firstname, lastname);
    }
}
