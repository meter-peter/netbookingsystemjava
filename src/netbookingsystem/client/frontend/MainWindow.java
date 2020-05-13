package netbookingsystem.client.frontend;

//import javafx.scene.control.DatePicker;
import netbookingsystem.server.core.base.Event;
import netbookingsystem.server.core.base.EventType;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


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
    Button button ;
    MainWindow clone;
    JButton jButton ;
    JButton logoutbtn;
    JButton refreshbtn;
    JDatePickerImpl datePickerfrom;
    JDatePickerImpl datePickerto;
    EventAdd eventadd;
    BookingSection bookingSection;



    //DatePicker datePickerFrom;
    //DatePicker datePickerTo;

    public MainWindow(String user, FrontendManager frontendManager){
        this.username=user;
        this.frontendManager=frontendManager;
        clone=this;
        jFrame=new JFrame("Siva");
        content=new JPanel();
        jSplitPane = new JSplitPane();
        defaultListModel = new DefaultListModel();
        eventJList = new JList<>(defaultListModel);
        options = new JPanel();
        button = new Button("Find Tickets");
        logoutbtn = new JButton("Logout");
        content.add(logoutbtn);
        refreshbtn = new JButton("Refresh Events");
        content.add(refreshbtn);

        options.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedEvent!=null){
                    System.out.println(selectedEvent.getTitle());
               bookingSection = new BookingSection(selectedEvent,frontendManager,clone);
            }}
        });



        //datePickerto.getModel().getValue();

        JLabel from = new JLabel("Από");
        JLabel to = new JLabel("Μέχρι");
//        datePickerTo = new DatePicker();
        type = new JComboBox<>();
        initDatepickers();
        options.add(type);
        options.add(from);
        options.add(datePickerfrom);
        options.add(to);
        options.add(datePickerto);

        type.addItem(EventType.CINEMA.getType());
        type.addItem(EventType.CONCERT.getType());
        type.addItem(EventType.PARTY.getType());
        type.addItem(EventType.THEATURE.getType());
        if (frontendManager.Sessionusername.equals("admin")) {
            jButton= new JButton("ADD A SHOW(ADMIN)");
            options.add(jButton);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    eventadd = new EventAdd(frontendManager);
                }
            });
        }


        jFrame.setContentPane(content);
        content.add(jSplitPane);
        jSplitPane.setRightComponent(options);
        jSplitPane.setLeftComponent(new JScrollPane(eventJList));
        content.setSize(1024,768);
        content.setVisible(true);
        jSplitPane.setVisible(true);
        jFrame.setSize(1024,768);
        jFrame.setVisible(true);

        refreshbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frontendManager.syncData();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
                try {
                    frontendManager.syncGUIevents();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });

            jFrame.pack();
        logoutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frontendManager.logout();
            }
        });
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
                for(int j=0;j<eventArrayList.get(i).getShows().size();j++){
                    if(datePickerfrom.getModel().getValue()!=null && datePickerto.getModel().getValue()!=null){
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                        Date datefrom = (Date)datePickerfrom.getModel().getValue();
                        Date eventdate = eventArrayList.get(i).getShows().get(j).getDayStart();
                        Date dateto = (Date) datePickerto.getModel().getValue();
                        System.out.println("datefrom"+datefrom.toString()+"eventdate"+eventdate+"dateto"+dateto);


                        if(datefrom.before(eventdate) && dateto.after(eventdate)){

                            defaultListModel.add(x,eventArrayList.get(i));
                            x++;

                        }
                    }else{
                        defaultListModel.add(x,eventArrayList.get(i));
                        x++;

                    }

                }

            }
        }
        eventJList.getSelectionModel().addListSelectionListener(e -> {
            selectedEvent =eventJList.getSelectedValue();
        });
    }

    public void initDatepickers(){
        UtilDateModel model = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePickerfrom = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePickerfrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatelist(frontendManager.getEvents());
            }
        });

        UtilDateModel modelto = new UtilDateModel();
        Properties pto = new Properties();
        pto.put("text.today", "Today");
        pto.put("text.month", "Month");
        pto.put("text.year", "Year");
        JDatePanelImpl datePanelto = new JDatePanelImpl(modelto, pto);
        datePickerto = new JDatePickerImpl(datePanelto, new DateLabelFormatter());
        datePanelto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatelist(frontendManager.getEvents());
            }
        });


    }


    public  JFrame getMainframe(){
        return jFrame;
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }



}
