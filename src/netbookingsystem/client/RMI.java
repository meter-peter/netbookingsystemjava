package netbookingsystem.client;

import netbookingsystem.RemoteInterface;
import netbookingsystem.server.auth.AuthStatus;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI {
    RemoteInterface stub;

        public RMI() {
            initRMI();

        }

       public void initRMI(){
           try {
               Registry registry = LocateRegistry.getRegistry(5555);

               stub = (RemoteInterface) Naming.lookup("rmi://localhost:5555/login");

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
    }



