package entity;

import com.demien.testloan.vo.ClientId;

public class Client {

    private ClientId clientId;
    private final String name;

    public Client(String name) {
        this.name = name;
    }


    public Client renameTo(String newName) {
        return new Client(newName);
    }


}
