package netbookingsystem.server.rmi;

import netbookingsystem.ClientInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiDriver {
    ClientFunctions remoteFunctions;


    public RmiDriver(ClientFunctions remoteFunctions){
        this.remoteFunctions = remoteFunctions;

    try {
        System.setProperty("java.rmi.server.hostname","192.168.1.15");
        LocateRegistry.createRegistry(5555);
        ClientInterface stub = (ClientInterface) UnicastRemoteObject.exportObject(remoteFunctions, 5555);


        Naming.rebind("rmi://localhost:5555/login"
                ,stub);
        System.err.println("Server ready");
    } catch (Exception e) {
        System.err.println("Server exception: " + e.toString());
        e.printStackTrace();
    }
}
}
