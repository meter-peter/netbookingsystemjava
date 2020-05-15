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
    MainWindow mainWindow;
    BookingSection clone;
    JFrame jframe;




    public BookingSection(Event event ,FrontendManager frontendManager, MainWindow mainWindow){
        jframe = new JFrame("Almost Ready");
        jframe.setSize(800,600);
        this.event=event;
        this.mainWindow =mainWindow;
        clone=this;
        jPanel = new JPanel();
        eventTitle = new JLabel(event.getTitle());
        defaultListModel = new DefaultListModel();
        shows = new JList<>(defaultListModel);
        bookbutton = new JButton("Proceed To Payment");
        jPanel.add(eventTitle);
        jPanel.add(shows);
        updateEventGUI();
        seats= new JComboBox<>();
        JLabel seatslabel= new JLabel("Seats");
        jPanel.add(seatslabel);
        jPanel.add(seats);
        jPanel.add(bookbutton);


        shows.getSelectionModel().addListSelectionListener(e -> {
            selected = shows.getSelectedValue();
            setJCombobox();



        });

        jframe.setLocationRelativeTo(null);
        jframe.add(jPanel);


        bookbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new PaymentPanel(frontendManager,clone);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
jPanel.setSize(800,600);
jPanel.setVisible(true);
        jframe.pack();
        jframe.setVisible(true);



    }


    public void updateEventGUI(){
        defaultListModel.clear();
        defaultListModel.addAll(event.getShows());
        shows.getSelectionModel().addListSelectionListener(e -> {
            selected = shows.getSelectedValue();
            setJCombobox();



        });

    }

    public void setEvent(Event event){
        this.event = event;
    }

    public void setJCombobox(){
        seats.removeAllItems();
        for(int i=0;i<selected.getAvailSeats();i++){
            Integer integer = i+1;
            seats.addItem(integer);
        }


    }
}
