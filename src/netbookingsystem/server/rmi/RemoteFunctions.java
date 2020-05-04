package netbookingsystem.server.rmi;

import javafx.concurrent.Task;

public class RemoteFunctions implements RemoteInterface{
    public RemoteFunctions(){
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }
}
