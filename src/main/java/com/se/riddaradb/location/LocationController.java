package com.se.riddaradb.location;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LocationController {

    final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/getlocations")
    Collection<LocationDto> getLocationEntry(){
        return locationService.getLocationEntries();
    }

    @GetMapping("/getlocationbyid/{id}")
    LocationDto getLocationEntryById(@PathVariable int id){
        return locationService.getLocationEntryById(id);
    }

    @PostMapping("/postlocation")
    LocationDto postLocationEntry(@RequestBody LocationDto locationDto){
        return locationService.saveLocationEntry(locationDto);
    }

    @PutMapping("/putlocation")
    LocationDto putLocationEntry(@RequestBody LocationDto locationDto){
        return locationService.saveLocationEntry(locationDto);
    }

    @DeleteMapping("/deletelocation/{id}")
    void deleteLocationEntry(@PathVariable int id){
        locationService.deleteLocationEntryById(id);
    }
}
