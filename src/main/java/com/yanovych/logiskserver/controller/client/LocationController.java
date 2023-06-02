package com.yanovych.logiskserver.controller.client;

import com.yanovych.logiskserver.dto.request.RequestLocationDto;
import com.yanovych.logiskserver.dto.response.ResponseLocationDto;
import com.yanovych.logiskserver.service.client.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client/locations")
@Tag(name = "/client/locations", description = "Client locations endpoint")
public class LocationController {
    private final LocationService locationService;

    @PostMapping()
    public ResponseEntity<ResponseLocationDto> createLocation(
            @RequestBody RequestLocationDto requestLocationDto) {
        ResponseLocationDto location = locationService.create(requestLocationDto);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ResponseLocationDto>> getLocations() {
        List<ResponseLocationDto> locations = locationService.getAll();
        if (locations == null || locations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLocationDto> getLocation(@PathVariable Long id) {
        ResponseLocationDto location = locationService.get(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ResponseLocationDto> updateLocation(
            @RequestBody RequestLocationDto requestLocationDto) {
        ResponseLocationDto location = locationService.update(requestLocationDto);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) {
        if (locationService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        locationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
