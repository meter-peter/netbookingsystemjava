package netbookingsystem.client.functions.rmi;

import netbookingsystem.client.frontend.FrontendManager;
import netbookingsystem.UserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;

public class RemoteUtils extends UnicastRemoteObject implements UserInterface {
    FrontendManager frontendManager;
    public RemoteUtils(FrontendManager frontendManager) throws RemoteException {
        super();
        this.frontendManager=frontendManager;
    }

    public void pushNotification(String message) throws RemoteException, ParseException {
        frontendManager.showMessage(message);

    }
}
