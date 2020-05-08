package netbookingsystem.client.functions;

import netbookingsystem.client.frontend.FrontendManager;

public class Controller {
    public Controller(){
        RMI rmi = new RMI();
        FrontendManager frontendManager = new FrontendManager(rmi);

    }

}
