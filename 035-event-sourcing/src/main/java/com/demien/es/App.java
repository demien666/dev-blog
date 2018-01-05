package com.demien.es;

import com.demien.es.domain.client.*;
import com.demien.es.system.EventBus;
import com.demien.es.system.event.Event;
import com.demien.es.system.event.EventType;

public class App {
    public void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        EventBus eventBus = new EventBus();
        ClientRepository clientRepository = new ClientRepository();
        ClientEventHandler clientEventHandler = new ClientEventHandler(clientRepository);
        eventBus.registerHandler(ClientCRUDEvent.class, clientEventHandler);
        String e1 = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.CREATE, new ClientVO("Joe", "NY")));
        pause();
        ClientEntity clientEntity = (ClientEntity) eventBus.getEventByGUID(e1).getResponse();
        System.out.println(clientEntity);


        String e2 = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.UPDATE, new ClientVO(clientEntity.getId(), "Joe", "NY")));
        pause();
        Event event = eventBus.getEventByGUID(e2);
        System.out.println(event.getErrorMessage());







    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new App().test();
    }
}
