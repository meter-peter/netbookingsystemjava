package netbookingsystem.client.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

public class PaymentPanel {
    BookingSection bookingSection;
    FrontendManager frontendManager;
    JTextField namefield;
    JTextField numberfield;
    JTextField datetextfield;
    JTextField cvvfielf;
    JComboBox<String> types;
    JPanel myPanel;



    public PaymentPanel(FrontendManager frontendManager , BookingSection bookingSection) throws Exception {
        JFrame jFrame = new JFrame("Pay Now");
        this.frontendManager= frontendManager;
        this.bookingSection = bookingSection;
        JLabel name =new JLabel("Owner's Name");
        namefield = new JTextField();
        JLabel number = new JLabel("Card's Number");
        numberfield = new JTextField();
        JLabel date= new JLabel("Expiration Date(Any Format)");
        datetextfield= new JTextField();
        JLabel cvv = new JLabel("CVV");
        cvvfielf = new JTextField();
        Button button = new Button("Pay Now");


        types = new JComboBox<>();
        types.addItem("VISA");
        types.addItem("MASTERCARD");


        myPanel = new JPanel(new GridLayout(5,1));
        myPanel.add(name);
        myPanel.add(namefield);
        myPanel.add(number);
        myPanel.add(numberfield);
        myPanel.add(date);
        myPanel.add(datetextfield);
        myPanel.add(cvv);
        myPanel.add(cvvfielf);
        myPanel.add(types);
        myPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frontendManager.book(bookingSection.event,bookingSection.selected,(Integer)bookingSection.seats.getSelectedItem());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }
        });


jFrame.setContentPane(myPanel);

jFrame.setLocationRelativeTo(null);
jFrame.setSize(1024,768);
        jFrame.pack();
        jFrame.setVisible(true);

        }

    }