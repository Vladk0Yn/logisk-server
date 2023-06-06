package com.yanovych.logiskserver.controller.client;

import com.yanovych.logiskserver.service.client.ClientAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
@Tag(name = "/client", description = "Client account endpoint")
public class ClientAccountController {
    private final ClientAccountService clientAccountService;
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getClientBalance() {
        BigDecimal balance = this.clientAccountService.getBalance();
        if (balance == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PutMapping("/balance/up")
    public ResponseEntity<BigDecimal> topUpClientBalance(@RequestParam BigDecimal amount) {
        BigDecimal balance = this.clientAccountService.topUpBalance(amount);
        if (balance == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}
