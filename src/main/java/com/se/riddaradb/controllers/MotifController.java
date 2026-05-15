package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.MotifDto;
import com.se.riddaradb.services.MotifService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MotifController {

    final MotifService motifService;

    public MotifController(MotifService motifService) {
        this.motifService = motifService;
    }

    @GetMapping("/getmotifentries")
    Collection<MotifDto> getMotifEntries(){
        return motifService.getMotifEntries();
    }

    @GetMapping("/getmotifentrybyid/{id}")
    MotifDto getMotifEntryById(@PathVariable int id){
        return motifService.getMotifEntryById(id);
    }

    @PostMapping("/postmotifentry")
    MotifDto postMotifEntry(@RequestBody MotifDto motifDto){
        return motifService.saveMotifEntry(motifDto);
    }

    @PutMapping("/putmotifentry")
    MotifDto putMotifEntry(@RequestBody MotifDto motifDto){
        return motifService.saveMotifEntry(motifDto);
    }

    @DeleteMapping("/deletemotifentry/{id}")
    void deleteMotifEntry(@PathVariable int id){
        motifService.deleteMotifEntryById(id);
    }
}
