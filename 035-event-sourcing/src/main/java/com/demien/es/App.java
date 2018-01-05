package com.demien.es;

import com.demien.es.domain.ClientVO;
import com.demien.es.domain.EventBus;
import com.demien.es.domain.VOChangeEvent;
import com.demien.es.domain.VOQuery;

public class App {

    public void test() {
        EventBus eventBus = new EventBus();
        VOQuery<ClientVO> query = new VOQuery<>(eventBus);
        eventBus.dispatchEvent(new VOChangeEvent<ClientVO>("qwe", new ClientVO("Joe", "NY")));
        eventBus.dispatchEvent(new VOChangeEvent<ClientVO>("asd", new ClientVO("Moe", "LA")));
        eventBus.dispatchEvent(new VOChangeEvent<ClientVO>("zxc", new ClientVO("Huan", "Houston")));
        eventBus.dispatchEvent(new VOChangeEvent<ClientVO>("qwe", new ClientVO("Joe", "Houston")));
        System.out.println(query.queryByEntityType(ClientVO.class, clientVO -> clientVO.getContactInfo().equals("Houston")));

    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new App().test();
    }
}
