package com.yanovych.logiskserver.controller.auth;

import com.yanovych.logiskserver.domain.User;
import com.yanovych.logiskserver.dto.request.RequestLoginDto;
import com.yanovych.logiskserver.dto.request.RequestRegisterDto;
import com.yanovych.logiskserver.dto.response.ResponseUserDto;
import com.yanovych.logiskserver.service.auth.AuthService;
import com.yanovych.logiskserver.service.auth.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "login user")
    public ResponseEntity<ResponseUserDto> login(@RequestBody RequestLoginDto requestLoginDto) {
        ResponseUserDto responseUserDto = authService.login(requestLoginDto);
        if (responseUserDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    @Operation(summary = "register user")
    public ResponseEntity<ResponseUserDto> register(@RequestBody RequestRegisterDto requestRegisterDto) {
        ResponseUserDto responseUserDto = authService.register(requestRegisterDto);
        if (responseUserDto == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
    }
}
