package com.demien.es;

import com.demien.es.domain.client.*;
import com.demien.es.domain.loan.LoanCreateEvent;
import com.demien.es.domain.loan.LoanCreateEventHandler;
import com.demien.es.domain.loan.LoanFinder;
import com.demien.es.system.EventBus;
import com.demien.es.system.SpringContext;
import com.demien.es.system.event.EventType;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {

    private EventBus eventBus = (EventBus) SpringContext.getBean(EventBus.class);
    private ClientFinder clientFinder = (ClientFinder) SpringContext.getBean(ClientFinder.class);
    private LoanFinder loanFinder = (LoanFinder) SpringContext.getBean(LoanFinder.class);

    private ClientCRUDEventHandler clientCRUDEventHandler = new ClientCRUDEventHandler();
    private ClientChangeStateEventHandler clientChangeStateEventHandler = new ClientChangeStateEventHandler();
    LoanCreateEventHandler loanCreateEventHandler = new LoanCreateEventHandler();


    @BeforeClass
    public void init() {
        eventBus.registerHandler(ClientCRUDEvent.class, clientCRUDEventHandler);
        eventBus.registerHandler(ClientChangeStateEvent.class, clientChangeStateEventHandler);
        eventBus.registerHandler(LoanCreateEvent.class, loanCreateEventHandler);
    }

    public void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {

        String guid = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.CREATE, new ClientCRUDRequest("Joe", "NY")));
        pause();

        /*
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


        guid = eventBus.dispatchEvent(new LoanCreateEvent(new LoanCreateRequest("", "100", "01.01.2017", "31.01.2017")));
        pause();
        event = eventBus.getEventByGUID(guid);
        System.out.println(event.getResponse());
        */

    }

}
