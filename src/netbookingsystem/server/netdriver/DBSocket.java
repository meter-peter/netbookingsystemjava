package netbookingsystem.server.netdriver;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;

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
        socket = new Socket("localhost", 8888);
    }

    public void initStreams () throws IOException {
        outputStream = socket.getOutputStream();
        out = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        in = new ObjectInputStream(inputStream);
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
