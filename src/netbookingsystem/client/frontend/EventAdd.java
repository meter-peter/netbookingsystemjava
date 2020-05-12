package netbookingsystem.client.frontend;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;
import netbookingsystem.server.core.base.Show;
import org.jdatepicker.JDatePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class EventAdd {
    FrontendManager frontendManager;
    JFrame jFrame;
    JPanel content;
    JSplitPane jSplitPane;
    JLabel Titlelabel;
    JTextField EventtitleTextfield;
    JPanel options;
    JLabel Ticketpricelabel;
    JTextField ticketpricefield;
    JLabel seatslabel;
    JTextField seatstextField;
    JComboBox<String> type;


    DefaultListModel defaultListModel;
    JList<Show> showstoadd;
    JButton addshow;
    JButton submitevent;


    public EventAdd(FrontendManager frontendManager){
        this.frontendManager=frontendManager;
        jFrame = new JFrame("Add Event");
        content = new JPanel();
        jSplitPane = new JSplitPane();
        Titlelabel = new JLabel("Event Title");
        EventtitleTextfield = new JTextField();
        EventtitleTextfield.setColumns(10);
        options = new JPanel(new GridLayout(4,2));
        Ticketpricelabel = new JLabel("Fee");
        ticketpricefield = new JTextField();

        //options.add
        // options.addC(datePickerFrom);
        // options.add(datePickerTo);
        type= new JComboBox<>();
        type.addItem(EventType.CINEMA.toString());
        type.addItem(EventType.CONCERT.toString());
        type.addItem(EventType.PARTY.toString());
        type.addItem(EventType.THEATURE.toString());
        seatslabel = new JLabel("Seats");
        seatstextField = new JTextField();
        defaultListModel = new DefaultListModel();
        showstoadd = new JList<>(defaultListModel);
        addshow = new JButton("Add Show");
        addshow.setBackground(Color.green);
        submitevent = new JButton("Submit Event");
        jFrame.setContentPane(content);
        JDatePicker jDatePicker;
        jSplitPane.setRightComponent(new JScrollPane(showstoadd));

        content.add(Titlelabel);
        content.add(EventtitleTextfield);
        content.add(jSplitPane);
        jSplitPane.setLeftComponent(options);
        options.add(Ticketpricelabel);
        options.add(ticketpricefield);
        options.add(seatslabel);
        options.add(seatstextField);
        options.add(addshow);
        options.add(submitevent);
        options.setVisible(true);
        options.setSize(800,600);
        jFrame.setSize(800,600);
        jFrame.pack();
        jFrame.setVisible(true);
        options.add(type);
        submitevent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submitevent();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        addshow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addshow();
            }
        });
    }

    public void submitevent() throws Exception {
        ArrayList list = new ArrayList(showstoadd.getModel().getSize());
        for (int i = 0; i < showstoadd.getModel().getSize(); i++) {
            list.add(showstoadd.getModel().getElementAt(i));
        }

        frontendManager.addEvent(new Event(EventtitleTextfield.getText(),type.getSelectedItem().toString(),list));
    }

    public void addshow(){
        defaultListModel.add(defaultListModel.size(),new Show(Date.from(Instant.now()),Integer.parseInt(seatstextField.getText()),Double.parseDouble(ticketpricefield.getText())));
    }
}

