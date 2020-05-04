package netbookingsystem.server.db;

import netbookingsystem.server.core.Action;
import netbookingsystem.server.core.Event;
import netbookingsystem.server.core.Protocol;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static netbookingsystem.server.core.Action.WRITE;


public class ServerDB {

    private ServerSocket serverSocket;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream out;
    private InputStream inputStream;
    private ObjectInputStream in;
    private static final String pathToEventsFile = "src/netbookingsystem/server/db/";

    public ServerDB () throws IOException {
        this.serverSocket = new ServerSocket(5555);
    }

    public static void main (String args[]) throws IOException, ClassNotFoundException {
        //init serverDB object
        ServerDB serverDB = new ServerDB();

        //wait for incoming connection
        while (true) {
            //open socket with client
            Socket socket = serverDB.getServerSocket().accept();
            System.out.println("Client connected!");
            serverDB.setSocket(socket);
            //after client's connection -> init Streams
            serverDB.initStreams();
            //get income packet
            Object ob = serverDB.getIn().readObject();
            if (ob instanceof Protocol) {
                Protocol protocol = (Protocol) ob;
                Action action = protocol.getAction();
                //read write action
                switch (action) {
                    case READ:
                        ArrayList<Event> events = serverDB.read();
                        Protocol successProtocol = new Protocol(action, events);
                        serverDB.getOut().writeObject(successProtocol);
                    case WRITE:
                        if (serverDB.write(protocol.getEvent())) {
                            successProtocol = new Protocol(action, true);
                        }
                        else {
                            Protocol errorProtocol = new Protocol(action, false);
                        }
                }
            }
            serverDB.closeConnention();
        }

    }

    public void closeConnention () throws IOException {
        getSocket().close();
    }

    //return events
    public ArrayList<Event> read () throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToEventsFile);
        ArrayList<Event> events = fileIO.readFromFile();
        return events;
    }

    public boolean write (Event event) throws IOException, ClassNotFoundException {
        FileIO fileIO = new FileIO(pathToEventsFile);
        if (fileIO.writeToFile(event)) {
            return true;
        }
        else {
            return false;
        }
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
