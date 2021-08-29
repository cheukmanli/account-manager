package com.acmebank.accountmanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {

    @NotNull
    private Integer fromAccount;
    @NotNull
    private Integer toAccount;
    @NotNull
    private BigDecimal amount;
}
