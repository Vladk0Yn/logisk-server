package com.yanovych.logiskserver.service.driver;

import java.math.BigDecimal;

public interface DriverAccountService {
    BigDecimal getBalance();
    BigDecimal withdrawBalance(BigDecimal amount);
}
