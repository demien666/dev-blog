package com.demien.es.domain.loan;

import com.demien.es.system.EventHandler;

public class LoanCreateEventHandler implements EventHandler<LoanCreateEvent> {


    @Override
    public void processEvent(LoanCreateEvent event) {
        LoanCreateRequest req = event.getRequest();
        try {
            LoanEntity loanEntity = new LoanEntity(req);
            event.markAsProcessed(loanEntity);
        } catch (Exception e) {
            event.markAsFailed(e);
        }
    }
}
