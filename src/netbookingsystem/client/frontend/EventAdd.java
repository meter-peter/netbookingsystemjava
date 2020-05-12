package netbookingsystem.client.frontend;

import netbookingsystem.server.core.base.Show;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class EventAdd {
    FrontendManager frontendManager;
    JFrame jFrame;
    JPanel content;
    JLabel Titlelabel;
    JTextField titleTextfield;
    DefaultListModel defaultListModel;
    JList<Show> showstoadd;
    JButton addshow;
    JButton submitevent;


    public EventAdd(FrontendManager frontendManager){
        this.frontendManager=frontendManager;
        content=new JPanel();
        jFrame=new JFrame("Add a Show");
        jFrame.setContentPane(content);
        Titlelabel = new JLabel("Show Title");
        titleTextfield= new JTextField();
        defaultListModel = new DefaultListModel();
        showstoadd= new JList<>(defaultListModel);
        addshow= new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("resources/add.ico"));
            addshow.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }



        submitevent=new JButton("Submit");

        content.add(Titlelabel);
        content.add(titleTextfield);
        content.add(addshow);
        content.add(showstoadd);
        content.add(submitevent);

        content.setVisible(true);
        jFrame.setVisible(true);
        jFrame.pack();







    }
}

