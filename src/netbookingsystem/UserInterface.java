package netbookingsystem;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;

public interface UserInterface extends Remote {

    public void pushNotification(String message) throws RemoteException, ParseException;


}
