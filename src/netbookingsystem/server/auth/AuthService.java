package netbookingsystem.server.auth;

import netbookingsystem.server.core.Controller;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    ArrayList<User> users;
    User toAuth;
    Controller controller;
    ArrayList<User> loggedInUsers;

    public AuthService(Controller controller) {
        loggedInUsers=new ArrayList<>();
        this.controller = controller;
    }


    public AuthStatus loginAccount(String username , String password) throws Exception {
        boolean flag = false;
        for (User tobesearched : users) { //ελεγχουμε ολη τη λιστα με τους λογαριασμούς
            if (tobesearched.getUsername().toLowerCase().equals(username.toLowerCase())) { //αν βρουμε το username συγκρίνουμε τα base64 των hashes;

                if (tobesearched.getPassword().equals(password)) {
                    System.out.println("SUCCESS");
                    toAuth = tobesearched;
                    controller.continuewithlogin();

                    flag = true;
                    return AuthStatus.SUCCESS;
                } else {
                    java.util.concurrent.TimeUnit.SECONDS.sleep(2); //timeout se periptwsh pou to exei lathos gia na kathysterhsoume se periptwsh bruteforce attack;
                    return AuthStatus.WRONG_PASS;
                }

            }

        }
    }


        public AuthStatus createAccount(String username , String password, String email , String firstname , String lastname) throws Exception {
            if(!containsName(users,username)){ //αν δεν υπάρχει ο λογαριασμός μπορεί να δημιουργηθεί



               User tobecreated = new User(username ,
                        password ,
                        email ,
                        firstname ,
                        lastname);

                System.out.println(tobecreated.toString());

                //αρχικοποιήσεις μετα γην εγγραφή
                users.add(tobecreated);
                loggedInUsers.add(tobecreated);

                usersManager.updateUsers(accounts);
                controller.continuewithregister(tobecreated);
                usersManager.saveAES(cryptoService.generateAES(),tobecreated);
                //controller.checkintegrity();
                return AuthStatus.SUCCESS;
            }
            return AuthStatus.DUPLICATE_USER;


        }

    private boolean containsName(List<User> list, String name) { //ελεγχος για το αν υπαρχει εγγεγραμένος χρήστης
        try {
            return list.stream().filter(o -> o.getUsername().equals(name)).findFirst().isPresent();
        } catch (NullPointerException e){}
        return false;
    }

}
