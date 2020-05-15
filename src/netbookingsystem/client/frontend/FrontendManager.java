package netbookingsystem.client.frontend;

import netbookingsystem.client.functions.rmi.RMI;
import netbookingsystem.client.functions.rmi.RemoteUtils;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

public class FrontendManager {

    RMI rmi;
    MainWindow mainWindow;
    LoginRegister loginRegister;
    ArrayList<Event> events;
    String Sessionusername;
    RemoteUtils remoteUtils;
    ArrayList<Ticket> ticketsbuffer;

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public FrontendManager(RMI rmi) throws RemoteException {
        this.rmi=rmi;
        events=new ArrayList<>();
        ticketsbuffer = new ArrayList<>();
        loginRegister = new LoginRegister(this);
        remoteUtils = new RemoteUtils(this);


    }

    public void cancelTicket(Ticket ticket) throws Exception {
        rmi.deleteTicket(ticket);

    }

    public void updateTickets() throws Exception {
        mainWindow.updatetickets(ticketsbuffer);


    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void logout(){
        mainWindow.getMainframe().dispose();
        loginRegister = new LoginRegister(this);
    }

    public void syncGUIevents() throws Exception {
       mainWindow.updatelist(events);
       mainWindow.updatetickets(ticketsbuffer);
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
        AuthStatus status = rmi.login(username, password,remoteUtils);
        if(status==AuthStatus.SUCCESS)
            onAuth(username);
        else showMessage(status.getMessage());
       return status;
    }

    public  AuthStatus register(String username, String password,String email , String firstname , String lastname) throws Exception{
        AuthStatus status =  rmi.register(username, password, email, firstname, lastname,remoteUtils);
        System.out.println(status.toString());
        if(status==AuthStatus.SUCCESS)
            onAuth(username);
       else showMessage(status.getMessage());
        return status;
    }

    public void syncData() throws Exception {
        this.events = rmi.getAvailableEvents();
        this.ticketsbuffer = rmi.getMyTickets(Sessionusername);

    }

    public void deleteAccount() throws Exception {
        rmi.deleteAccount(Sessionusername);
    }

    public void deleteEvent(String eventid) throws Exception {
        rmi.deleteEvent(eventid);
        syncData();
        syncGUIevents();
    }
    public void book(Event event, Show show , int seats) throws Exception {
        String eventid = event.getId();
        if(!rmi.book(Sessionusername,event,show,seats)){
            showMessage("Λυπούμαστε αλλά η διάθεση των εισιτηρίων για αυτό το θέαμα έχει σταματήσει"); }

        syncData();
        for(Event e:events){
            if(e.getId().equals(eventid));
            mainWindow.bookingSection.setEvent(e);
            SwingUtilities.updateComponentTreeUI(mainWindow.bookingSection.jframe);
        }
            mainWindow.bookingSection.jframe.dispose();
        syncGUIevents();
        mainWindow.updatetickets(rmi.getMyTickets(Sessionusername));

    }

    public void addEvent(Event event) throws Exception {
        rmi.addEvent(event);
        syncData();
        syncGUIevents();
    }


    private void ShowMessage(String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    public void showMessage(String message) throws Exception {
        ShowMessage(message);



    }
}
