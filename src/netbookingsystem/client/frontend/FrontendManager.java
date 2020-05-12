package netbookingsystem.client.frontend;

import netbookingsystem.client.functions.RMI;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class FrontendManager {

    RMI rmi;
    MainWindow mainWindow;
    LoginRegister loginRegister;
    ArrayList<Event> events;
    String Sessionusername;

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
        SwingUtilities.updateComponentTreeUI(mainWindow.jFrame);

        }


    private void onAuth(String username) throws Exception {
        this.Sessionusername=username;
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
        System.out.println(status.toString());
        if(status==AuthStatus.SUCCESS)
            onAuth(username);
        return status;
    }

    public void syncData() throws RemoteException {
        this.events = rmi.getAvailableEvents();
        System.out.println(events);
    }

    public void book(Event event, Show show , int seats) throws Exception {
        rmi.book(Sessionusername,event,show,seats);
        syncData();
        syncGUIevents();
    }

    public void addEvent(Event event) throws Exception {
        rmi.addEvent(event);
        syncData();
        syncGUIevents();
    }
}
