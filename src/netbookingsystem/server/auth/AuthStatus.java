package netbookingsystem.server.auth;

public enum AuthStatus {
    SUCCESS(0,"SUCCESS"),
    NOT_FOUND(1, "User Not Found"),
    DUPLICATE_USER(5007, "This user already exists."),
    WRONG_PASS(4,"Your password may be invalid");


    private final int id;
    private final String message;

    AuthStatus(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() { return id; }
    public String getMessage() { return message; }




}
