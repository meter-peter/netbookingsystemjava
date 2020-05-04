package netbookingsystem.server.rmi;

import netbookingsystem.RemoteInterface;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiDriver {
    RemoteFunctions remoteFunctions;


    public RmiDriver(RemoteFunctions remoteFunctions){
        this.remoteFunctions = remoteFunctions;

    try {
        LocateRegistry.createRegistry(5555);
        RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(remoteFunctions, 5555);


        Naming.rebind("rmi://localhost:5555/login"
                ,stub);
        System.err.println("Server ready");
    } catch (Exception e) {
        System.err.println("Server exception: " + e.toString());
        e.printStackTrace();
    }
}
}
