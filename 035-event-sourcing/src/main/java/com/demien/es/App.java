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
        ClientCRUDEventHandler clientCRUDEventHandler = new ClientCRUDEventHandler(clientRepository);
        eventBus.registerHandler(ClientCRUDEvent.class, clientCRUDEventHandler);
        ClientChangeStateEventHandler clientChangeStateEventHandler = new ClientChangeStateEventHandler(clientRepository);
        eventBus.registerHandler(ClientChangeStateEvent.class, clientChangeStateEventHandler);

        String guid = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.CREATE, new ClientCRUDRequest("Joe", "NY")));
        pause();
        ClientEntity clientEntity = (ClientEntity) eventBus.getEventByGUID(guid).getResponse();
        System.out.println(clientEntity);


        guid = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.UPDATE, new ClientCRUDRequest(clientEntity.getId(), "Joe", "HOUSTON")));
        pause();
        Event event = eventBus.getEventByGUID(guid);
        System.out.println(event.getErrorMessage());

        guid = eventBus.dispatchEvent(new ClientChangeStateEvent(EventType.APPROVE, new ClientChangeStateRequest(clientEntity.getId(), -1, ClientState.APPROVED)));
        pause();
        System.out.println(clientEntity);

        guid = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.UPDATE, new ClientCRUDRequest(clientEntity.getId(), "Joe", "HOUSTON")));
        pause();
        event = eventBus.getEventByGUID(guid);
        System.out.println(event.getState());

        System.out.println(clientEntity);













    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new App().test();
    }
}
