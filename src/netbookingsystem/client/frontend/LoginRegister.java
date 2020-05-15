package netbookingsystem.client.frontend;


import netbookingsystem.client.frontend.FrontendManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;

public class LoginRegister extends JFrame {
    FrontendManager frontendManager;

    public LoginRegister (FrontendManager frontendManager) {

        setLocationRelativeTo(null);
        this.frontendManager = frontendManager;
        setTitle("Please Register To Continue");
        //init comps
        JLabel firstLabel = new JLabel("First Name");
        JTextField firstTextfield = new JTextField();
        JLabel lastLabel = new JLabel("Last Name");
        JTextField lastTextfield = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        JTextField emailTextfield = new JTextField();
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameTextfield = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordTextfield = new JPasswordField();
        passwordTextfield.setEchoChar('*');

        JLabel confirmLabel = new JLabel("Password Confirmation");
        JPasswordField confirmTextfield = new JPasswordField();
        confirmTextfield.setEchoChar('*');
        JLabel regusernameLabel = new JLabel("Username");
        JTextField regusernameTextfield = new JTextField();
        JLabel regpasswordLabel = new JLabel("Password");
        JPasswordField regpasswordTextfield = new JPasswordField();
        regpasswordTextfield.setEchoChar('*');

        //init register panel
        JPanel registerpanel = new JPanel(new GridLayout(8,2));
        registerpanel.add(firstLabel);
        registerpanel.add(firstTextfield);
        registerpanel.add(lastLabel);
        registerpanel.add(lastTextfield);
        registerpanel.add(emailLabel);
        registerpanel.add(emailTextfield);
        registerpanel.add(regusernameLabel);
        registerpanel.add(regusernameTextfield);

        registerpanel.add(confirmLabel);
        registerpanel.add(confirmTextfield);
        registerpanel.add(regpasswordLabel);
        registerpanel.add(regpasswordTextfield);
        JButton submitregister = new JButton("Submit Register");
        JLabel changetologin = new JLabel("Or Click here to Log in");
        registerpanel.add(submitregister);
        registerpanel.add(changetologin);

        //init login panel
        JPanel loginpanel = new JPanel(new GridLayout(4,2));
        loginpanel.add(usernameLabel);
        loginpanel.add(usernameTextfield);
        loginpanel.add(passwordLabel);
        loginpanel.add(passwordTextfield);
        Button loginbtn = new Button("Enter");
        loginpanel.add(loginbtn);
        JLabel returnregister = new JLabel("Get me back to register");
        loginpanel.add(returnregister);
        loginpanel.setVisible(true);
        registerpanel.setVisible(true);

        JPanel content = new JPanel(new CardLayout());
        content.add(registerpanel,"Register");
        content.add(loginpanel,"Login");
        content.setVisible(true);
        add(content);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CardLayout cl = (CardLayout)(content.getLayout());
        cl.show(content, "Register");
        pack();



        changetologin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cl.show(content,"Login");
                setTitle("Login");

            }
        });

        returnregister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cl.show(content,"Register");
                setTitle("Register");
            }
        });

        submitregister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(regpasswordTextfield.getText().equals(confirmTextfield.getText())){
                    try {
                        frontendManager.register(regusernameTextfield.getText(),firstTextfield.getText(),lastTextfield.getText(),emailTextfield.getText(),regpasswordTextfield.getText());
                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            else {
                JOptionPane.showMessageDialog(content, "Passwords do not match");

            }}
        });

        loginbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                  frontendManager.login(usernameTextfield.getText(),passwordTextfield.getText());
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.setVisible(true);
    }





}
