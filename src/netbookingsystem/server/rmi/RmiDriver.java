package netbookingsystem.server.rmi;

import netbookingsystem.ClientInterface;

import java.net.Inet4Address;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiDriver {
    ClientFunctions remoteFunctions;


    public RmiDriver(ClientFunctions remoteFunctions){
        this.remoteFunctions = remoteFunctions;

    try {
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
