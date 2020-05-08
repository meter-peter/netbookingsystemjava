package netbookingsystem.client.frontend;

import netbookingsystem.client.functions.RMI;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;

import java.util.ArrayList;

public class FrontendManager {

    RMI rmi;
    MainWindow mainWindow;
    LoginRegister loginRegister;
    ArrayList<Event> events;

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public FrontendManager(RMI rmi){
        this.rmi=rmi;
        events=new ArrayList<>();
        loginRegister = new LoginRegister(this);


    }


    private void onAuth(String username){
        mainWindow = new MainWindow(username , this);
        loginRegister.dispose();


    }

    public AuthStatus login(String username, String password) throws Exception {
        AuthStatus status = rmi.login(username, password);
        if(status==AuthStatus.SUCCESS)
            onAuth(username);
       return status;
    }

    public  AuthStatus register(String username, String password,String email , String firstname , String lastname) throws Exception{
        AuthStatus status =  rmi.register(username, password, email, firstname, lastname);
        if(status==AuthStatus.SUCCESS)
            onAuth(username);
        return status;
    }
}
