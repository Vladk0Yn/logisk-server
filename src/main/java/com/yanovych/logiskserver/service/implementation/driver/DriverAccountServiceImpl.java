package com.yanovych.logiskserver.service.implementation.driver;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Driver;
import com.yanovych.logiskserver.repository.ClientRepository;
import com.yanovych.logiskserver.repository.DriverRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.driver.DriverAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverAccountServiceImpl implements DriverAccountService {
    private final DriverRepository driverRepository;
    private final SessionUserService sessionUserService;
    @Override
    public BigDecimal getBalance() {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        if (driver == null) {
            return null;
        }
        return driver.getUser().getBalance();
    }

    @Override
    public BigDecimal withdrawBalance(BigDecimal amount) {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        if (driver == null) {
            return null;
        }
        if (driver.getUser().getBalance().compareTo(amount) < 0) {
            return null;
        }
        driver.getUser().setBalance(driver.getUser().getBalance().subtract(amount));
        this.driverRepository.save(driver);
        return driver.getUser().getBalance();
    }
}
