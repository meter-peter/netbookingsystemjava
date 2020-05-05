package netbookingsystem.server.rmi;


import netbookingsystem.ClientInterface;
import netbookingsystem.server.auth.AuthService;
import netbookingsystem.server.auth.AuthStatus;

import java.rmi.RemoteException;

public class ClientFunctions implements ClientInterface {
    AuthService authService;

    public ClientFunctions(AuthService authService) throws RemoteException {
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
