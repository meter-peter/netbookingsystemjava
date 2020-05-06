package netbookingsystem.client.frontend;

import javafx.scene.control.DatePicker;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Main {
    String username;
    JFrame jFrame;
    JPanel content;
    JSplitPane jSplitPane;
    DefaultListModel defaultListModel;
    JList<Event> eventJList;
    JPanel options;
    JComboBox<String> type;
    FrontendManager frontendManager;
    DatePicker datePickerFrom;
    DatePicker datePickerTo;

    public Main(String user, FrontendManager frontendManager){
        this.username=user;
        this.frontendManager=frontendManager;
        jFrame=new JFrame("Siva");
        content=new JPanel();
        jSplitPane = new JSplitPane();
        defaultListModel = new DefaultListModel();
        eventJList = new JList<>();
        options = new JPanel();

        JLabel from = new JLabel("Από");
        datePickerFrom = new DatePicker();
        JLabel to = new JLabel("Μέχρι");
        datePickerTo = new DatePicker();
        type = new JComboBox<>();

        options.add(type);
        options.add(from);
        options.add
        options.addC(datePickerFrom);
        options.add(datePickerTo);

        type.addItem(EventType.CINEMA.getType());
        type.addItem(EventType.CONCERT.getType());
        type.addItem(EventType.PARTY.getType());
        type.addItem(EventType.THEATURE.getType());




        jFrame.add(content);
        content.add(jSplitPane);
        jSplitPane.setRightComponent(options);
        jSplitPane.setLeftComponent(eventJList);


    }




}
