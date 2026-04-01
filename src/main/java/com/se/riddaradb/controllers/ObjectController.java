package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.dtos.ObjectDto;
import com.se.riddaradb.services.ObjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ObjectController {

    final ObjectService objectService;

    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping("/getobjectentries")
    Collection<ObjectDto> getObjectEntries(){
        return objectService.getObjectEntries();
    }

    @GetMapping("/getobjectentrybyid/{id}")
    ObjectDto getObjectEntryById(@PathVariable int id){
        return objectService.getObjectEntryById(id);
    }

    @PostMapping("/postobjectentry")
    ObjectDto postObjectEntry(@RequestBody ObjectDto objectDto){
        return objectService.saveObjectEntry(objectDto);
    }

    @PutMapping("/putobjectentry")
    ObjectDto putFolkloreEntry(@RequestBody ObjectDto objectDto){
        return objectService.saveObjectEntry(objectDto);
    }

    @DeleteMapping("/deleteobjectentry/{id}")
    void deleteObjectEntry(@PathVariable int id){
        objectService.deleteObjectEntryById(id);
    }
}
