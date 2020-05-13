package netbookingsystem.client;

import netbookingsystem.client.functions.ClientController;

import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws RemoteException {
        new ClientController();
    }
}
