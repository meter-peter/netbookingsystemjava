package netbookingsystem.server.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import netbookingsystem.server.core.Controller;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    ArrayList<User> users;
    User toAuth;
    Controller controller;
    ArrayList<User> loggedInUsers;
    File usersFile;

    public AuthService(Controller controller) throws IOException {

        this.controller = controller;
        this.users=new ArrayList<>();
        this.loggedInUsers=new ArrayList<>();
        initUsers();

    }

    public void initUsers() throws IOException {
        usersFile = new File("users.json");
        if (!usersFile.exists()) {
            usersFile.createNewFile();
            loggedInUsers = new ArrayList<>();
        } else {
            loggedInUsers = getUsers();
        }
    }


    public AuthStatus loginAccount(String username, String password) throws Exception {
        boolean flag = false;
        for (User tobesearched : users) { //ελεγχουμε ολη τη λιστα με τους λογαριασμούς
            if (tobesearched.getUsername().toLowerCase().equals(username.toLowerCase())) { //αν βρουμε το username συγκρίνουμε τα base64 των hashes;

                if (tobesearched.getPassword().equals(password)) {
                    System.out.println("SUCCESS");
                    toAuth = tobesearched;
                    controller.continuewithlogin(toAuth);

                    flag = true;
                    return AuthStatus.SUCCESS;
                } else {
                    java.util.concurrent.TimeUnit.SECONDS.sleep(2); //timeout se periptwsh pou to exei lathos gia na kathysterhsoume se periptwsh bruteforce attack;
                    return AuthStatus.WRONG_PASS;
                }

            }

        }
        return AuthStatus.WRONG_PASS;
    }




    public AuthStatus createAccount(String username, String password, String email, String firstname, String lastname) throws Exception {
        if (!containsName(users, username)) { //αν δεν υπάρχει ο λογαριασμός μπορεί να δημιουργηθεί


            User tobecreated = new User(username,
                    password,
                    email,
                    firstname,
                    lastname);

            System.out.println(tobecreated.toString());

            //αρχικοποιήσεις μετα γην εγγραφή
            users.add(tobecreated);
           loggedInUsers.add(tobecreated);

            updateUsers(users);
            // controller.continuewithregister(tobecreated);
            //controller.checkintegrity();
            return AuthStatus.SUCCESS;
        }
        return AuthStatus.DUPLICATE_USER;


    }

    private boolean containsName(List<User> list, String name) { //ελεγχος για το αν υπαρχει εγγεγραμένος χρήστης
        try {
            return list.stream().filter(o -> o.getUsername().equals(name)).findFirst().isPresent();
        } catch (NullPointerException e) {
        }
        return false;
    }


    public ArrayList<User> getUsers() { //μετατροπή απο json αρχειο σε arraylist
        try {
            Reader reader = Files.newBufferedReader(Paths.get(usersFile.getPath()));

            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();

        }
        ArrayList<User> temp = new ArrayList<>();
        return temp;
    }


    public void updateUsers(List<User> appusers) { //ανανέωση του αρχείου
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(usersFile.getPath()));
            Gson gson = new Gson();
            gson.toJson(appusers, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
