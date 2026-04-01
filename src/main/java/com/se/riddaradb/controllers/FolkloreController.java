package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.dtos.FolkloreDto;
import com.se.riddaradb.services.BibService;
import com.se.riddaradb.services.FolkloreService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class FolkloreController {

    final FolkloreService folkloreService;

    public FolkloreController(FolkloreService folkloreService) {
        this.folkloreService = folkloreService;
    }

    @GetMapping("/getfolkloreentries")
    Collection<FolkloreDto> getFolkloreEntries(){
        return folkloreService.getFolkloreEntries();
    }

    @GetMapping("/getfolkloreentrybyid/{id}")
    FolkloreDto getFolkloreEntryById(@PathVariable int id){
        return folkloreService.getFolkloreEntryById(id);
    }

    @PostMapping("/postfolkloreentry")
    FolkloreDto postFolkloreEntry(@RequestBody FolkloreDto folkloreDto){
        return folkloreService.saveFolkloreEntry(folkloreDto);
    }

    @PutMapping("/putfolkloreentry")
    FolkloreDto putFolkloreEntry(@RequestBody FolkloreDto folkloreDto){
        return folkloreService.saveFolkloreEntry(folkloreDto);
    }

    @DeleteMapping("/deletefolkloreentry/{id}")
    void deleteFolkloreEntry(@PathVariable int id){
        folkloreService.deleteFolkloreEntryById(id);
    }
}
