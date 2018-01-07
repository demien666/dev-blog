package com.demien.es.domain.loan;

import com.demien.es.system.EntityFinder;
import com.demien.es.system.EventBus;

public class LoanFinder extends EntityFinder<LoanEntity> {
    public LoanFinder(EventBus eventBus) {
        super(eventBus, LoanEntity.class);
    }
}
