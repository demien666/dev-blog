package com.demien.es;

import com.demien.es.domain.client.*;
import com.demien.es.domain.loan.LoanCreateEvent;
import com.demien.es.domain.loan.LoanCreateEventHandler;
import com.demien.es.domain.loan.LoanFinder;
import com.demien.es.system.EventBus;
import com.demien.es.system.SpringContext;
import com.demien.es.system.event.Event;
import com.demien.es.system.event.EventState;
import com.demien.es.system.event.EventType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainTest {

    private static EventBus eventBus = (EventBus) SpringContext.getBean(EventBus.class);
    private static ClientFinder clientFinder = (ClientFinder) SpringContext.getBean(ClientFinder.class);
    private static LoanFinder loanFinder = (LoanFinder) SpringContext.getBean(LoanFinder.class);

    private static ClientCRUDEventHandler clientCRUDEventHandler = new ClientCRUDEventHandler();
    private static ClientChangeStateEventHandler clientChangeStateEventHandler = new ClientChangeStateEventHandler();
    private static LoanCreateEventHandler loanCreateEventHandler = new LoanCreateEventHandler();


    @BeforeClass
    public static void init() {
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

    public Event sendEvent(Event event) {
        eventBus.dispatchEvent(event);
        pause();
        return event;
    }

    public Event createClient(String name, String contactInfo) {
        return sendEvent(new ClientCRUDEvent(EventType.CREATE, new ClientCRUDRequest(name, contactInfo)));
    }


    public Event updateClient(long id, String name, String contactInfo) {
        return sendEvent(new ClientCRUDEvent(EventType.UPDATE, new ClientCRUDRequest(id, name, contactInfo)));
    }

    public Event updateClientState(long clientId, ClientState state) {
        return sendEvent(new ClientChangeStateEvent(new ClientChangeStateRequest(clientId, state)));
    }


    @Test
    public void itShouldCreateClient() {
        Event event = createClient("Joe", "NY");

        assertTrue(event.getState() == EventState.PROCESSED);
        ClientEntity client = (ClientEntity) event.getResponse();
        assertTrue(client.getId() > 0);
        assertTrue(client.getName().equals("Joe"));
        assertTrue(client.getContactInfo().equals("NY"));

        assertTrue(clientFinder.getAll().contains(client));
    }

    @Test
    public void itShouldFailIfUpdatingClientInPendingState() {
        Event event = createClient("Pending", "PE");
        ClientEntity client = (ClientEntity) event.getResponse();
        event = updateClient(client.getId(), "new name", "new info");
        assertTrue(event.getState() == EventState.FILED);
    }

    @Test
    public void itShouldUpdateClientStateToApproved() {
        Event event = createClient("For Approval", "PE");
        ClientEntity client = (ClientEntity) event.getResponse();
        assertTrue(client.getState() == ClientState.PENDING);
        updateClientState(client.getId(), ClientState.APPROVED);
        assertTrue(client.getState() == ClientState.APPROVED);
    }

    @Test
    public void itShouldSucceedIfUpdatingClientInApprovedState() {
        Event event = createClient("Approved", "PE");
        ClientEntity client = (ClientEntity) event.getResponse();
        updateClientState(client.getId(), ClientState.APPROVED);
        event = updateClient(client.getId(), "new name", "new info");
        assertTrue(event.getState() == EventState.PROCESSED);
        assertTrue(client.getName().equals("new name"));
    }

    //Test
    public void test() {

        String guid = eventBus.dispatchEvent(new ClientCRUDEvent(EventType.CREATE, new ClientCRUDRequest("Joe", "NY")));
        pause();
        Assert.assertTrue(clientFinder.getAll().size() == 1);

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
