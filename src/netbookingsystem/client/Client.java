package netbookingsystem.client;

import netbookingsystem.client.frontend.FrontendManager;
import netbookingsystem.client.functions.RMI;

public class Client {
    public static void main(String[] args){
        RMI rmi = new RMI();
        new FrontendManager(rmi);
    }
}
