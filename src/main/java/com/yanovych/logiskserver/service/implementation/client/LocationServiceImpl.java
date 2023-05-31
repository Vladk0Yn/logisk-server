package com.yanovych.logiskserver.service.implementation.client;

import com.yanovych.logiskserver.domain.Location;
import com.yanovych.logiskserver.dto.mapper.RequestLocationDtoMapper;
import com.yanovych.logiskserver.dto.mapper.ResponseLocationDtoMapper;
import com.yanovych.logiskserver.dto.request.RequestLocationDto;
import com.yanovych.logiskserver.dto.response.ResponseLocationDto;
import com.yanovych.logiskserver.repository.LocationRepository;
import com.yanovych.logiskserver.service.auth.SessionUserService;
import com.yanovych.logiskserver.service.client.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final SessionUserService sessionUserService;
    private final LocationRepository locationRepository;

    private List<Location> getClientLocations() {
        return locationRepository.findLocationsByClient_Id(sessionUserService.getAuthenticatedUser().getId());
    }

    @Override
    public ResponseLocationDto create(RequestLocationDto locationDto) {
        Location queryLocation = RequestLocationDtoMapper.INSTANCE.dtoToLocation(locationDto);
        queryLocation.setClient(sessionUserService.getAuthenticatedUser().getClient());
        return ResponseLocationDtoMapper.INSTANCE.locationToDto(locationRepository.save(queryLocation));
    }

    @Override
    public List<ResponseLocationDto> getAll() {
        List<Location> locations = getClientLocations();
        return locations.stream()
                .map(ResponseLocationDtoMapper.INSTANCE::locationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseLocationDto get(Long id) {
        List<Location> locations = getClientLocations();
        return locations.stream()
                .filter(location -> Objects.equals(location.getId(), id))
                .map(ResponseLocationDtoMapper.INSTANCE::locationToDto)
                .findFirst().orElse(null);
    }

    @Override
    public ResponseLocationDto update(RequestLocationDto locationDto) {
        List<Location> locations = getClientLocations();
        Location updatedLocation = RequestLocationDtoMapper.INSTANCE.dtoToLocation(locationDto);
        if (locations.stream().anyMatch(location -> location.getId().equals(updatedLocation.getId()))) {
            return ResponseLocationDtoMapper.INSTANCE.locationToDto(locationRepository.save(updatedLocation));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        List<Location> locations = getClientLocations();
        Location clientLocation = locations.stream().filter(location -> Objects.equals(location.getId(), id)).findFirst().orElse(null);
        if (clientLocation == null) {
            return;
        }
        if (clientLocation.getOrdersFrom().isEmpty() && clientLocation.getOrdersTo().isEmpty()) {
            locationRepository.deleteById(id);
        }
    }
}
