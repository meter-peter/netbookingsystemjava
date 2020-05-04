package netbookingsystem.server.rmi;


import netbookingsystem.server.auth.AuthService;

public class RemoteFunctions implements RemoteInterface{
    AuthService authService;

    public RemoteFunctions(AuthService authService) {
        this.authService = authService;
    }

    public void login(String username, String password){


    }

}
