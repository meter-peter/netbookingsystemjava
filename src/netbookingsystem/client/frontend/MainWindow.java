package netbookingsystem.client.frontend;

//import javafx.scene.control.DatePicker;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class MainWindow {
    String username;
    JFrame jFrame;
    JPanel content;
    JSplitPane jSplitPane;
    DefaultListModel defaultListModel;
    JList<Event> eventJList;
    JPanel options;
    JComboBox<String> type;
    FrontendManager frontendManager;
    ArrayList<Event> eventArrayList;
    Event selectedEvent;
    //DatePicker datePickerFrom;
    //DatePicker datePickerTo;

    public MainWindow(String user, FrontendManager frontendManager){
        this.username=user;
        this.frontendManager=frontendManager;
        jFrame=new JFrame("Siva");
        content=new JPanel();
        jSplitPane = new JSplitPane();
        defaultListModel = new DefaultListModel();
        eventJList = new JList<>(defaultListModel);
        options = new JPanel();

        JLabel from = new JLabel("Από");
//        datePickerFrom = new DatePicker();
        JLabel to = new JLabel("Μέχρι");
//        datePickerTo = new DatePicker();
        type = new JComboBox<>();

        options.add(type);
        options.add(from);
        //options.add
       // options.addC(datePickerFrom);
       // options.add(datePickerTo);

        type.addItem(EventType.CINEMA.getType());
        type.addItem(EventType.CONCERT.getType());
        type.addItem(EventType.PARTY.getType());
        type.addItem(EventType.THEATURE.getType());




        jFrame.add(content);
        content.add(jSplitPane);
        jSplitPane.setRightComponent(options);
        jSplitPane.setLeftComponent(new JScrollPane(eventJList));
        content.setSize(1024,768);
        content.setVisible(true);
        jSplitPane.setVisible(true);
        jFrame.setSize(1024,768);
        jFrame.setVisible(true);


        type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updatelist(frontendManager.getEvents());
            }
        });

    }

    public JList<Event> getEventJList() {
        return eventJList;
    }

    public void updatelist(ArrayList<Event> list){
        eventArrayList=list;
        System.out.println(eventArrayList);
        defaultListModel.clear();
        int x =0 ;
        for(int i=0;i<list.size();i++){
            if(eventArrayList.get(i).getEventType().getType().equals(type.getSelectedItem())){
                defaultListModel.add(x,eventArrayList.get(i));
                x++;
            }
        }
        eventJList.getSelectionModel().addListSelectionListener(e -> {
            selectedEvent =eventJList.getSelectedValue();
            new BookingSection(selectedEvent,frontendManager);
        });
    }
}
