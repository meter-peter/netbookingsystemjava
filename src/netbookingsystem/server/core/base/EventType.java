package netbookingsystem.server.core.base;

public enum EventType{
    THEATURE(1,"Θεατρική Παράσταση"),
    CONCERT(2,"Συναυλία"),
    PARTY(3,"Πάρτυ"),
    CINEMA(4,"Σινεμά"),
    ;

    String type;
    int idd;

    EventType(int id ,String s){
        type = s;
        idd=id;
    }

    public String getType() {
        return type;
    }

    public int getIdd() {
        return idd;
    }
}

