package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;

import java.io.*;
import java.util.ArrayList;

public class FileIO {

    private String pathToFile;
    private File file;

    public FileIO (String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        this.file = new File(this.pathToFile);
    }

    public boolean writeToFile (Event event) throws IOException, ClassNotFoundException {
        try {
            ArrayList<Event> events = readFromFile();
            events.add(event);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
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

    public ArrayList<Event> readFromFile () throws IOException, ClassNotFoundException {
        ArrayList<Event> events = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return events;
        }
        else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            events = (ArrayList<Event>) in.readObject();
            return events;
        }
    }

}
