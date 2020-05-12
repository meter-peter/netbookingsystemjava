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
        System.out.println("A Client Has been coneected");
    }

    public void initSockets() throws IOException, ClassNotFoundException {
        this.serverSocket = new ServerSocket(8888);
        this.socket =serverSocket.accept();
        System.out.println("W8ing 4 connection");
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        out = new ObjectOutputStream(outputStream);
        in = new ObjectInputStream(inputStream);
    }

    public void send(Protocol protocol) throws IOException {
        getOut().writeObject(protocol);
    }

    public ObjectOutputStream getOut() {
        return out;
    }
    public ObjectInputStream getIn() {
        return in;
    }

}
