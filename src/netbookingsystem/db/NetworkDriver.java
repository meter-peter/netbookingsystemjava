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
        initStreams();

    }

    public void initSockets() throws IOException, ClassNotFoundException {
        this.serverSocket = new ServerSocket(8888);
        this.socket =serverSocket.accept();
        System.out.println("W8ing 4 connection");


    }
    public void run() throws IOException, ClassNotFoundException {
        while (socket.isConnected()){
            acceptpacket();
        }
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


    public void closeConnention () throws IOException {
        getSocket().close();
    }

    public void initStreams () throws IOException {
        this.outputStream = this.socket.getOutputStream();
        this.out = new ObjectOutputStream(this.outputStream);
        this.inputStream = this.socket.getInputStream();
        this.in = new ObjectInputStream(this.inputStream);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
}
