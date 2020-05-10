package netbookingsystem.client.frontend;

import netbookingsystem.client.functions.RMI;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;

import java.rmi.RemoteException;
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

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void syncGUIevents(){
       mainWindow.updatelist(events);

        }


    private void onAuth(String username) throws RemoteException {
        syncData();
        loginRegister.dispose();
        mainWindow = new MainWindow(username , this);
        syncGUIevents();



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

    public void syncData() throws RemoteException {
        this.events = rmi.getAvailableEvents();
        System.out.println(events);
    }
}
