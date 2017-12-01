package com.demien.ddd.loan.domain;

import com.demien.ddd.security.domain.User;
import com.demien.ddd.shared.annotations.Aggregate;
import com.demien.ddd.shared.annotations.Entity;
import com.demien.ddd.shared.domain.AbstractEntity;

import java.util.Date;

@Entity
@Aggregate
public class Loan extends AbstractEntity {
    private Client client;
    private User creator;
    private Date createDate;


}
