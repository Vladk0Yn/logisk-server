package com.yanovych.logiskserver.service.implementation.client;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.repository.ClientRepository;
import com.yanovych.logiskserver.repository.UserRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.client.ClientAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ClientAccountServiceImpl implements ClientAccountService {

    private final ClientRepository clientRepository;
    private final SessionUserService sessionUserService;

    @Override
    public BigDecimal getBalance() {
        Client client = sessionUserService.getAuthenticatedUser().getClient();
        if (client == null) {
            return null;
        }
        return client.getUser().getBalance();
    }

    @Override
    public BigDecimal topUpBalance(BigDecimal amount) {
        Client client = sessionUserService.getAuthenticatedUser().getClient();
        if (client == null) {
            return null;
        }
        client.getUser().setBalance(client.getUser().getBalance().add(amount));
        this.clientRepository.save(client);
        return client.getUser().getBalance();
    }
}
