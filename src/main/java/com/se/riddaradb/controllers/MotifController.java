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

    @GetMapping("/getmotifs")
    Collection<MotifDto> getMotifEntries(){
        return motifService.getMotifEntries();
    }

    @GetMapping("/getmotifbyid/{id}")
    MotifDto getMotifEntryById(@PathVariable int id){
        return motifService.getMotifEntryById(id);
    }

    @GetMapping("/getrootmotifs")
    Collection<MotifDto> getRootMotifs(){
        return motifService.getRootMotifs();
    }

    @GetMapping("/getchildmotifsbyid/{id}")
    Collection<MotifDto> getChildMotifsById(@PathVariable int id){
        return motifService.getChildMotifs(id);
    }

    @PostMapping("/postmotif")
    MotifDto postMotifEntry(@RequestBody MotifDto motifDto){
        return motifService.saveMotifEntry(motifDto);
    }

    @PutMapping("/putmotif")
    MotifDto putMotifEntry(@RequestBody MotifDto motifDto){
        return motifService.saveMotifEntry(motifDto);
    }

    @DeleteMapping("/deletemotif/{id}")
    void deleteMotifEntry(@PathVariable int id){
        motifService.deleteMotifEntryById(id);
    }
}
