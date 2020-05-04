package netbookingsystem.client;

import netbookingsystem.RemoteInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI {

        public RMI() {
            try {
                // Getting the registry
                Registry registry = LocateRegistry.getRegistry(5555);

                // Looking up the registry for the remote object
                RemoteInterface stub = (RemoteInterface) Naming.lookup("rmi://localhost:5555/login");

                // Calling the remote method using the obtained object
                stub.register("Niko","Eisai","Poly","Megalo","Prezoni");

                // System.out.println("Remote method invoked");
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }

    }



