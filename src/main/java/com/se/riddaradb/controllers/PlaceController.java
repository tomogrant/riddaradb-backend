package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.dtos.PlaceDto;
import com.se.riddaradb.services.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PlaceController {

    final PlaceService placeService;
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/getplaceentries")
    Collection<PlaceDto> getPlaceEntry(){
        return placeService.getPlaceEntries();
    }

    @GetMapping("/getplaceentrybyid/{id}")
    PlaceDto getPlaceEntryById(@PathVariable int id){
        return placeService.getPlaceEntryById(id);
    }

    @PostMapping("/postplaceentry")
    PlaceDto postPlaceEntry(@RequestBody PlaceDto placeDto){
        return placeService.savePlaceEntry(placeDto);
    }

    @PutMapping("/putplaceentry")
    PlaceDto putPlaceEntry(@RequestBody PlaceDto placeDto){
        return placeService.savePlaceEntry(placeDto);
    }

    @DeleteMapping("/deleteplaceentry/{id}")
    void deletePlaceEntry(@PathVariable int id){
        placeService.deletePlaceEntryById(id);
    }
}
