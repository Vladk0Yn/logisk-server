package com.yanovych.logiskserver.service.auth;

import com.yanovych.logiskserver.domain.Client;
import com.yanovych.logiskserver.domain.Driver;
import com.yanovych.logiskserver.domain.Role;
import com.yanovych.logiskserver.domain.User;
import com.yanovych.logiskserver.dto.mapper.ResponseUserDTOMapper;
import com.yanovych.logiskserver.dto.request.RequestLoginDto;
import com.yanovych.logiskserver.dto.request.RequestRegisterDto;
import com.yanovych.logiskserver.dto.response.ResponseUserDto;
import com.yanovych.logiskserver.repository.ClientRepository;
import com.yanovych.logiskserver.repository.DriverRepository;
import com.yanovych.logiskserver.repository.RoleRepository;
import com.yanovych.logiskserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseUserDto login(RequestLoginDto requestLoginDto) {
        String login = requestLoginDto.login();
        String password = requestLoginDto.password();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String loggedInUsername = userDetails.getUsername();
            User user = userRepository.findByEmail(loggedInUsername).orElse(null);
            return ResponseUserDTOMapper.INSTANCE.userToDto(user);
        } catch (AuthenticationException e) {
            return null;
        }
    }

    public ResponseUserDto register(RequestRegisterDto requestRegisterDto) {
        User user = new User();
        user.setEmail(requestRegisterDto.email());
        user.setName(requestRegisterDto.name());
        user.setBalance(BigDecimal.valueOf(0));
        user.setPhoneNumber(requestRegisterDto.phoneNumber());
        user.setPassword(passwordEncoder.encode(requestRegisterDto.password()));
        for (Role role : roleRepository.findAll()) {
            if (requestRegisterDto.role().toUpperCase().equals(role.getName())) {
                if (role.getName().equals("DRIVER")) {
                    user.setRole(role);
                    Driver driver = new Driver();
                    driver.setUser(user);
                    user.setDriver(driver);
                    driverRepository.save(driver);
                }
                if (role.getName().equals("CLIENT")) {
                    user.setRole(role);
                    Client client = new Client();
                    client.setUser(user);
                    user.setClient(client);
                    clientRepository.save(client);
                }
            }
        }
        return ResponseUserDTOMapper.INSTANCE.userToDto(userRepository.save(user));
    }
}
