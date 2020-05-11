package netbookingsystem.client.frontend;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.Show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingSection {


    Event event;
    JPanel jPanel;
    JLabel eventTitle;
    JComboBox<Integer> seats;
    DefaultListModel defaultListModel;
    JList<Show> shows;
    Show selected;
    JButton bookbutton;





    public BookingSection(Event event ,FrontendManager frontendManager){
        this.event=event;
        jPanel = new JPanel();
        eventTitle = new JLabel(event.getTitle());
        defaultListModel = new DefaultListModel();
        shows = new JList<>(defaultListModel);
        bookbutton = new JButton("Proceed To Payment");
        jPanel.add(eventTitle);
        jPanel.add(shows);
        defaultListModel.addAll(event.getShows());
        seats= new JComboBox<>();

        shows.getSelectionModel().addListSelectionListener(e -> {
            selected = shows.getSelectedValue();
            setJCombobox();



        });

        bookbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    frontendManager.book(event,selected,(Integer)seats.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }





    public void setJCombobox(){
        for(int i=0;i<selected.getAvailSeats();i++){
            Integer integer = i+1;
            seats.addItem(integer);
        }


    }
}
