package netbookingsystem.client.functions.rmi;

import netbookingsystem.ClientInterface;
import netbookingsystem.UserInterface;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;

import java.net.Inet4Address;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class RMI {
    ClientInterface stub;
            public RMI() {
            initRMI();

        }
        public void initRMI(){
           try {
               Registry registry = LocateRegistry.getRegistry(5555);
               System.setProperty("java.rmi.server.hostname", Inet4Address.getLocalHost().getHostAddress());
               stub = (ClientInterface) Naming.lookup("rmi://localhost:5555/login");

           } catch (Exception e) {
               System.err.println("Client exception: " + e.toString());
               e.printStackTrace();
           }

        }
        public ArrayList<Ticket> getMyTickets(String username) throws Exception {
                return stub.getUserTickets(username);
        }

        public AuthStatus login(String username , String password, UserInterface userInterface) throws Exception {
           return stub.login(username, password,userInterface);

        }
        public AuthStatus register(String username, String password, String email , String firstname , String lastname,UserInterface userInterface) throws Exception {
            return stub.register(username,password,email,firstname,lastname,userInterface);

        }

        public ArrayList<Event> getAvailableEvents() throws RemoteException {
            return stub.getAvailableEvents();
        }

        public boolean book(String username , Event event , Show show , int seats) throws Exception {
           return stub.book(username,event,show,seats);
        }

        public void addEvent(Event event) throws Exception {
            stub.addEvent(event);
        }

        public void deleteEvent(String eventid) throws Exception {
                stub.deleteEvent(eventid);
        }
        public void deleteTicket(Ticket ticket) throws Exception {
                stub.deleteTicket(ticket);
        }

        public void deleteAccount(String username) throws Exception {
                stub.deleteAccount(username);
        }

}



