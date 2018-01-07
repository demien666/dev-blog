package com.demien.es.domain.loan;

import com.demien.es.system.event.Event;
import com.demien.es.system.event.EventType;

public class LoanCreateEvent extends Event<LoanCreateRequest, LoanEntity> {

    public LoanCreateEvent(LoanCreateRequest request) {
        super(EventType.CREATE, request);
    }

}
