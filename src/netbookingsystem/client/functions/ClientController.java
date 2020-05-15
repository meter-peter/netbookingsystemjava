package netbookingsystem.client.functions;

import netbookingsystem.client.frontend.FrontendManager;
import netbookingsystem.client.functions.rmi.RMI;
import netbookingsystem.client.functions.rmi.RemoteUtils;

import java.rmi.RemoteException;

public class ClientController {
    RMI rmi;
    FrontendManager frontendManager;

    public ClientController() throws RemoteException {
       rmi = new RMI();
       frontendManager = new FrontendManager(rmi);
    }





}
