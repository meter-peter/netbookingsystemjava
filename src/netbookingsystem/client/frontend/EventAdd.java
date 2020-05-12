package netbookingsystem.client.frontend;

import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;
import netbookingsystem.server.core.base.Show;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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
    JDatePickerImpl datePicker;
    JComboBox<Integer> minutes;
    JComboBox<Integer> hours;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    JTextField placefield;



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
        options = new JPanel(new GridLayout(9,2));
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
        initDatepickers();
        jSplitPane.setRightComponent(new JScrollPane(showstoadd));
        inittimebox();

        content.add(Titlelabel);
        content.add(EventtitleTextfield);
        content.add(jSplitPane);
        jSplitPane.setLeftComponent(options);
        options.add(Ticketpricelabel);
        options.add(ticketpricefield);
        options.add(seatslabel);
        options.add(seatstextField);

        options.setVisible(true);
        options.setSize(800,600);
        jFrame.setSize(800,600);
        JLabel typelabel = new JLabel("Type:");
        options.add(typelabel);

        options.add(type);
        JLabel datelabel = new JLabel("Date:");
        options.add(datelabel);
        options.add(datePicker);
        JLabel time = new JLabel("Hour");
        JLabel mins = new JLabel("Min");
        options.add(time);
        options.add(hours);
        options.add(mins);
        options.add(minutes);
        JLabel placelbl=new JLabel("Place");
        options.add(placelbl);
        placefield= new JTextField();
        options.add(placefield);
        options.add(addshow);
        options.add(submitevent);
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

        jFrame.pack();
        jFrame.setVisible(true);
    }



    public void submitevent() throws Exception {
        ArrayList list = new ArrayList(showstoadd.getModel().getSize());
        for (int i = 0; i < showstoadd.getModel().getSize(); i++) {
            list.add(showstoadd.getModel().getElementAt(i));
        }

        frontendManager.addEvent(new Event(EventtitleTextfield.getText(),type.getSelectedItem().toString(),list));
    }

    public void addshow(){
        defaultListModel.add(defaultListModel.size(),new Show(df.format(datePicker.getModel().getValue())
                ,hours.getSelectedItem().toString()+":"+minutes.getSelectedItem().toString()
                ,Integer.parseInt(seatstextField.getText())
                ,Double.parseDouble(ticketpricefield.getText()),placefield.getText()));
    }

    public void initDatepickers() {
        UtilDateModel model = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    public void inittimebox(){
        hours = new JComboBox<>();
        for(int i=0;i<24;i++){
            hours.addItem(i);
        }
        minutes = new JComboBox<>();
        for(int i=0; i<60;i++){
            minutes.addItem(i);
        }
    }
}

