package netbookingsystem.db;
import netbookingsystem.db.Controller;
import netbookingsystem.server.netdriver.Protocol;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class NetworkDriver {
    private Controller controller;
    private ServerSocket serverSocket;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream out;
    private InputStream inputStream;
    private ObjectInputStream in;


    public NetworkDriver(Controller controller) throws IOException, ClassNotFoundException {
        this.controller = controller;
        initSockets();
        initStreams();
        System.out.println("A Client Has been coneected");
        while(true){
            acceptpacket();
        }

    }

    public void initSockets() throws IOException, ClassNotFoundException {
        this.serverSocket = new ServerSocket(8888);
        this.socket =serverSocket.accept();
        System.out.println("W8ing 4 connection");


    }


    public void send(Protocol protocol) throws IOException {
        getOut().writeObject(protocol);
    }

    public void acceptpacket() throws IOException, ClassNotFoundException {
        while (true) {
            Object ob =getIn().readObject();
            System.out.println("pernaei!");
            if (ob instanceof Protocol) {
                Protocol protocol = (Protocol) ob;
                controller.driveFunction(protocol);
            }
        }
    }

    public void initStreams () throws IOException {
        this.outputStream = this.socket.getOutputStream();
        this.out = new ObjectOutputStream(this.outputStream);
        this.inputStream = this.socket.getInputStream();
        this.in = new ObjectInputStream(this.inputStream);
    }

    public ObjectOutputStream getOut() {
        return out;
    }
    public ObjectInputStream getIn() {
        return in;
    }

}
