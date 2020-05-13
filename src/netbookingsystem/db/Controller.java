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

public class Controller extends Thread{

    FileIO fileIO;
    NetworkDriver networkDriver;
    ArrayList<Event> serverEvents;
    ArrayList<Ticket> generatedTickets;


    public Controller() throws IOException, ClassNotFoundException {
        this.start();
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
        System.out.println("Driving Function :" + packet.getParams().get(0) +packet.getParams().get(1));
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
                    returnTickets();
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

            case "MODIFY":
                    for (int i = 0; i < serverEvents.size(); i++) {
                            if (serverEvents.get(i).getId().equals(packet.getParams().get(1))) {
                                for (Show show : serverEvents.get(i).getShows()) {
                                    if (show.getId().equals(packet.getParams().get(2))) {
                                        show.setAvailSeats(Integer.parseInt(packet.getParams().get(3)));
                                        show.setTicketPrice(Double.parseDouble(packet.getParams().get(4)));
                                        updateEvents(serverEvents);
                                    }
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


        response.setToSendevents(serverEvents);
        System.out.println(networkDriver);
        updateEvents(serverEvents);
        networkDriver.send(response);
    }

    public void returnTickets() throws IOException, ClassNotFoundException {
        ArrayList<String> params = new ArrayList<>();
        params.add("RETURN");
        Protocol response = new Protocol(params);
        response.setToSendTickets(generatedTickets);
        networkDriver.getOut().writeObject(response);
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
