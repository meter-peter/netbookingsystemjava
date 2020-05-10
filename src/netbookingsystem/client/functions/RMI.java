package netbookingsystem.client.functions;

import netbookingsystem.ClientInterface;
import netbookingsystem.server.auth.AuthStatus;
import netbookingsystem.server.core.base.Event;

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

               stub = (ClientInterface) Naming.lookup("rmi://localhost:5555/login");

           } catch (Exception e) {
               System.err.println("Client exception: " + e.toString());
               e.printStackTrace();
           }

        }

        public AuthStatus login(String username , String password) throws Exception {
           return stub.login(username, password);

        }
        public AuthStatus register(String username, String password, String email , String firstname , String lastname) throws Exception {
            return stub.register(username,password,email,firstname,lastname);

        }

        public ArrayList<Event> getAvailableEvents() throws RemoteException {
            return stub.getAvailableEvents();
        }
}



