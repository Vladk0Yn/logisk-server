package com.yanovych.logiskserver.service.implementation.driver;

import com.yanovych.logiskserver.domain.Driver;
import com.yanovych.logiskserver.domain.Transport;
import com.yanovych.logiskserver.dto.mapper.RequestTransportDtoMapper;
import com.yanovych.logiskserver.dto.mapper.ResponseTransportDtoMapper;
import com.yanovych.logiskserver.dto.request.RequestTransportDto;
import com.yanovych.logiskserver.dto.response.ResponseOrderDto;
import com.yanovych.logiskserver.dto.response.ResponseTransportDto;
import com.yanovych.logiskserver.repository.DriverRepository;
import com.yanovych.logiskserver.repository.TransportRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.driver.TransportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final DriverRepository driverRepository;
    private final SessionUserService sessionUserService;

    @Override
    public ResponseTransportDto create(RequestTransportDto requestTransportDto) {
        Transport transport = RequestTransportDtoMapper.INSTANCE.dtoToTransport(requestTransportDto);
        if (transport.getLoadLength() == null || transport.getLoadWidth() == null || transport.getLoadHeight() == null || transport.getLoadCapacity() == null) {
            return null;
        }
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        transport = transportRepository.save(transport);
        driver.setTransport(transport);
        transport.setDriver(driver);
        driverRepository.save(driver);
        return ResponseTransportDtoMapper.INSTANCE.transportToDto(transport);
    }

    @Override
    public ResponseTransportDto get() {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        Transport transport = driver.getTransport();
        if (transport == null) {
            return null;
        }
        return ResponseTransportDtoMapper.INSTANCE.transportToDto(this.transportRepository.save(transport));
    }

    @Override
    public ResponseTransportDto update(RequestTransportDto requestTransportDto) {
        Driver driver = sessionUserService.getAuthenticatedUser().getDriver();
        Transport oldTransport = driver.getTransport();
        Transport updatedTransport = RequestTransportDtoMapper.INSTANCE.dtoToTransport(requestTransportDto);
        if (updatedTransport == null || oldTransport == null) {
            return null;
        }
        driver.setTransport(updatedTransport);
        updatedTransport.setDriver(driver);
        return ResponseTransportDtoMapper.INSTANCE.transportToDto(this.transportRepository.save(updatedTransport));
    }
}
