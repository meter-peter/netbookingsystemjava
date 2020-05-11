package netbookingsystem.db;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;
import netbookingsystem.server.core.base.Show;
import netbookingsystem.server.core.base.Ticket;
import netbookingsystem.server.netdriver.Protocol;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Controller{

    FileIO fileIO;
    NetworkDriver networkDriver;
    ArrayList<Event> serverEvents;
    ArrayList<Ticket> generatedTickets;


    public Controller() throws IOException, ClassNotFoundException {
        fileIO = new FileIO();
        serverEvents = fileIO.readEventsFromFile();
        generatedTickets = fileIO.readTicketsFromFile();
        networkDriver = new NetworkDriver(this);
        while (true) {
            acceptpacket();
        }
    }

    public void acceptpacket() throws IOException, ClassNotFoundException {
        Object ob =networkDriver.getIn().readObject();
        System.out.println("pernaei!");
        if (ob instanceof Protocol) {
            Protocol protocol = (Protocol) ob;
           driveFunction(protocol);
        }
    }

    //deserialize and call functions
    public void driveFunction(Protocol packet) throws IOException, ClassNotFoundException {
        System.out.println("Driving Function :" + packet.getParams().get(0));
        switch (packet.getParams().get(0)) {
            case "ADD":
                if (packet.getParams().get(1).equals("EVENT"))
                    addEvent(packet.getEvent());
                else if (packet.getParams().get(1).equals("TICKET"))
                    addTicket(packet.getTicket());
                break;

            case "GET":
                if (packet.getParams().get(1).equals("EVENTS")) {
                    returnEvents();
                } else if (packet.getParams().get(1).equals("TICKETS")) {
                    readTickets();
                }
                break;

            case "DELETE":
                if (packet.getParams().get(1).equals("EVENT")) {
                    for (int i = 0; i < serverEvents.size(); i++) {
                        if (serverEvents.get(i).getId().equals(packet.getParams().get(2))) {
                            serverEvents.remove(i);
                            updateEvents(serverEvents);
                        }

                    }
                } else if (packet.getParams().get(1).equals("TICKET")) {
                    for (int i = 0; i < generatedTickets.size(); i++) {
                        if (generatedTickets.get(i).getId().equals(packet.getParams().get(2))) {
                            generatedTickets.remove(i);
                            updateTickets(generatedTickets);
                        }

                    }
                }
            default:
                Errrespond();
                break;
        }
    }

    public void Errrespond() throws IOException {
        ArrayList<String> params = new ArrayList<>();
        params.add("ERROR");
        Protocol response = new Protocol(params);
        networkDriver.getOut().writeObject(response);


    }

    public void returnEvents() throws IOException, ClassNotFoundException {
        ArrayList<String> params = new ArrayList<>();
        params.add("RETURN");
        Protocol response = new Protocol(params);

        System.out.println(serverEvents);

        ArrayList<Event> tempe= new ArrayList<>();
        ArrayList<Show> temps = new ArrayList<>();
        temps.add(new Show(Date.from(Instant.now()),100,25.50));
        tempe.add(new Event("Η Αλικη","PARTY",temps));
        response.setToSendevents(tempe);
        System.out.println(networkDriver);
        updateEvents(serverEvents);
        networkDriver.send(response);


    }


    public void returnTickets() {

    }

    public void addEvent(Event event) throws IOException, ClassNotFoundException {
        serverEvents.add(event);
        updateEvents(serverEvents);
        ArrayList<String> responseparams = new ArrayList<>();
        responseparams.add("Event Added");
        Protocol response = new Protocol(responseparams);
        networkDriver.getOut().writeObject(response);

    }

    public void addTicket(Ticket ticket) throws IOException, ClassNotFoundException {
        generatedTickets.add(ticket);
        updateTickets(generatedTickets);
        ArrayList<String> responseparams = new ArrayList<>();
        responseparams.add("Ticket Added");
        Protocol response = new Protocol(responseparams);
        networkDriver.getOut().writeObject(response);

    }

    public boolean updateEvents(ArrayList<Event> events) throws IOException, ClassNotFoundException {
        if (fileIO.writeEventsToFile(events)) {
            return true;
        } else {
            return false;
        }
    }

    //return tickets
    public ArrayList<Ticket> readTickets() throws IOException, ClassNotFoundException {
        ArrayList<Ticket> tickets = fileIO.readTicketsFromFile();
        return tickets;
    }

    //write new ticket
    public boolean updateTickets(ArrayList<Ticket> tickets) throws IOException, ClassNotFoundException {
        if (fileIO.writeTicketsToFile(tickets)) {
            return true;
        } else {
            return false;
        }
    }

}
