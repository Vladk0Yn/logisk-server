package com.yanovych.logiskserver.service.client;

import java.math.BigDecimal;

public interface ClientAccountService {
    BigDecimal getBalance();
    BigDecimal topUpBalance(BigDecimal amount);
}
