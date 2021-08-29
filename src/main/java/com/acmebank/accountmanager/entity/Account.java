package com.acmebank.accountmanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Account {

    @Id
    private Integer id;
    private BigDecimal balance;

    public Account() {}

    public Account(Integer id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }
}
