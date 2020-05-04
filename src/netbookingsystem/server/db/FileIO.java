package netbookingsystem.server.db;

import netbookingsystem.server.core.Event;

import java.io.*;
import java.util.ArrayList;

public class FileIO {

    private String pathToFile;
    private File file;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public FileIO (String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        file = new File(this.pathToFile);
        fileOutputStream = new FileOutputStream(file);
        fileInputStream = new FileInputStream(file);
        out = new ObjectOutputStream(fileOutputStream);
        in = new ObjectInputStream(fileInputStream);
    }

    public ArrayList<Event> readFromFile () throws IOException, ClassNotFoundException {
        ArrayList<Event> events = new ArrayList<>();
        if (!file.exists()) {
            file.createNewFile();
            return events;
        }
        else {
            events = (ArrayList<Event>) in.readObject();
            return events;
        }
    }

    public boolean writeToFile (Event event) throws IOException, ClassNotFoundException {
        try {
            ArrayList<Event> events = readFromFile();
            events.add(event);
            out.writeObject(events);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
