package com.demien.es.domain.loan;

import com.demien.es.domain.Entity;
import com.demien.es.domain.client.ClientEntity;
import com.demien.es.domain.client.ClientFinder;
import com.demien.es.domain.client.ClientState;
import com.demien.es.system.SpringContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanEntity extends Entity {

    private ClientEntity client;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;
    private BigDecimal payed = new BigDecimal(0);
    private LoanState state = LoanState.PENDING;

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");

    public LoanEntity(LoanCreateRequest req) throws Exception {
        Date d1 = formatter.parse(req.getStartDate());
        Date d2 = formatter.parse(req.getEndDate());
        LoanEntity loanEntity = new LoanEntity(req);
        loanEntity.setAmount(new BigDecimal(req.getAmount()));
        ClientFinder clientFinder = (ClientFinder) SpringContext.getBean(ClientFinder.class);
        ClientEntity client = clientFinder.findEntityById(Long.parseLong(req.getClientId())).get(0);
        if (client == null) {
            exception("Not able to find the client with id:" + req.getClientId());
        }
        if (client.getState() != ClientState.APPROVED) {
            exception("Client state should be APPROVED! Instea of:" + client.getState());
        }

        loanEntity.setClient(client);
        loanEntity.setStartDate(d1);
        loanEntity.setEndDate(d2);
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPayed() {
        return payed;
    }

    public void setPayed(BigDecimal payed) {
        this.payed = payed;
    }

    public LoanState getState() {
        return state;
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LoanEntity{" +
                "client=" + client +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", payed=" + payed +
                ", state=" + state +
                '}';
    }
}
