package netbookingsystem.client.frontend;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;

import javax.swing.*;
import java.awt.*;

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

    public Main(String user, FrontendManager frontendManager){
        this.username=user;
        this.frontendManager=frontendManager;
        jFrame=new JFrame("Siva");
        content=new JPanel();
        jSplitPane = new JSplitPane();
        defaultListModel = new DefaultListModel();
        eventJList = new JList<>();
        options = new JPanel();

        type = new JComboBox<>();
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
