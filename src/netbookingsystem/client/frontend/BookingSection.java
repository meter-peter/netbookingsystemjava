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





    public BookingSection(Event event ,FrontendManager frontendManager, MainWindow mainWindow){
        JFrame jframe = new JFrame("Almost Ready");
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
        defaultListModel.addAll(event.getShows());
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
        jframe.pack();
        jframe.setVisible(true);

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


    }





    public void setJCombobox(){
        for(int i=0;i<selected.getAvailSeats();i++){
            Integer integer = i+1;
            seats.addItem(integer);
        }


    }
}
