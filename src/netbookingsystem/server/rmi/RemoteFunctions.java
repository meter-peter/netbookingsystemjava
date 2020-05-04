package netbookingsystem.server.rmi;


import netbookingsystem.RemoteInterface;
import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.AuthStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteFunctions implements RemoteInterface {
    AuthService authService;

    public RemoteFunctions(AuthService authService) throws RemoteException {
        super();
        this.authService = authService;
    }

    public AuthStatus login(String username, String password) throws Exception {
       return authService.loginAccount(username, password);


    }


    public AuthStatus register(String username , String password , String email , String firstname, String lastname) throws Exception {
        return authService.createAccount(username, password, email, firstname, lastname);
    }

}
