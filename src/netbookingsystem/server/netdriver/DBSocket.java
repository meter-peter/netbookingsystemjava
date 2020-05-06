package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class DBSocket {

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Protocol toSend;
    private Protocol toReceive;

    public DBSocket () throws IOException {
        initSocket();//make connection with db server
        initStreams();//after socket connection init streams
    }

    public void initSocket () throws IOException {
        socket = new Socket("localhost", 5555);
    }

    public void initStreams () throws IOException {
        outputStream = socket.getOutputStream();
        out = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        in = new ObjectInputStream(inputStream);
    }

    public ArrayList<Event> getEventsFromDB () throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(); //protocol to send //set parameters
        protocol.setAction(Action.READ);
        protocol.setType(Type.EVENT);
        protocol.setStatus(Status.GET);
        setToSend(protocol);//set protocol to send
        getOut().writeObject(getToSend()); //send to db
        Protocol receive = (Protocol) getIn().readObject();
        setToReceive(receive);
        if (getToReceive().getStatus() == Status.POST) {
            return getToReceive().getEvents();
        }
        else {
            System.out.println("Error");
            return null;
        }
    }

    public boolean writeEventsToDB (ArrayList<Event> events) throws IOException, ClassNotFoundException {
       Protocol protocol = new Protocol();
       protocol.setAction(Action.WRITE);
       protocol.setType(Type.EVENT);
       protocol.setStatus(Status.GET);
       setToSend(protocol);
       getOut().writeObject(getToSend());
       Protocol receive = (Protocol) getIn().readObject();
       setToReceive(receive);
       if (getToReceive().getStatus() == Status.POST) {
           return true;
       }
       else {
           return false;
       }
    }

    //getter && setter
    public void closeConnection () throws IOException {
        socket.close();
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public Protocol getToSend() {
        return toSend;
    }

    public void setToSend(Protocol toSend) {
        this.toSend = toSend;
    }

    public Protocol getToReceive() {
        return toReceive;
    }

    public void setToReceive(Protocol toReceive) {
        this.toReceive = toReceive;
    }
}
