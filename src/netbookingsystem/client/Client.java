package netbookingsystem.client;

public class Client {
    public static void main(String[] args){
        RMI rmi = new RMI();
        new FrontendManager(rmi);
    }
}
