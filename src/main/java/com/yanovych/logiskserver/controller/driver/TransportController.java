package com.yanovych.logiskserver.controller.driver;

import com.yanovych.logiskserver.dto.request.RequestTransportDto;
import com.yanovych.logiskserver.dto.response.ResponseDriverDto;
import com.yanovych.logiskserver.dto.response.ResponseTransportDto;
import com.yanovych.logiskserver.service.driver.TransportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/driver/transport")
@Tag(name = "/driver/transport", description = "Driver transport endpoint")
public class TransportController {

    private final TransportService transportService;

    @PostMapping("")
    public ResponseEntity<ResponseTransportDto> createDriverTransport(@RequestBody RequestTransportDto requestTransportDto) {
        ResponseTransportDto transport = transportService.create(requestTransportDto);
        if (transport == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(transport, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ResponseTransportDto> getTransport() {
        ResponseTransportDto transport = transportService.get();
        if (transport == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transport, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ResponseTransportDto> updateTransport(@RequestBody RequestTransportDto requestTransportDto) {
        ResponseTransportDto transport = transportService.update(requestTransportDto);
        if (transport == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(transport, HttpStatus.OK);
    }
}
