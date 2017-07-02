package entity;

import com.demien.testloan.vo.ClientId;

public class Client {

    private final ClientId clientId;
    private final String name;

    public Client(ClientId clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }


    public Client renameTo(String newName) {
        return new Client(clientId, newName);
    }


}
