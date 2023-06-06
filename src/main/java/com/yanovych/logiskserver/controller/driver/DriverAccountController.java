package com.yanovych.logiskserver.controller.driver;

import com.yanovych.logiskserver.service.driver.DriverAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/driver")
@Tag(name = "/driver", description = "Driver account endpoint")
public class DriverAccountController {
    private final DriverAccountService driverAccountService;
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getDriverBalance() {
        BigDecimal balance = this.driverAccountService.getBalance();
        if (balance == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PutMapping("/balance/withdraw")
    public ResponseEntity<BigDecimal> withdrawDriverBalance(@RequestParam BigDecimal amount) {
        BigDecimal balance = this.driverAccountService.withdrawBalance(amount);
        if (balance == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}
