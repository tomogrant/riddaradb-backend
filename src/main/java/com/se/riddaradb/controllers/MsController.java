package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.dtos.MsDto;
import com.se.riddaradb.services.MsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MsController {

    final MsService msService;

    public MsController(MsService msService) {
        this.msService = msService;
    }

    @GetMapping("/getmsentries")
    Collection<MsDto> getMsEntries(){
        return msService.getMsEntries();
    }

    @GetMapping("/getmsentrybyid/{id}")
    MsDto getMsEntryById(@PathVariable int id){
        return msService.getMsEntryById(id);
    }

    @PostMapping("/postmsentry")
    MsDto postMsEntry(@RequestBody MsDto msDto){
        return msService.saveMsEntry(msDto);
    }

    @PutMapping("/putmsentry")
    MsDto putMsEntry(@RequestBody MsDto msDto){
        return msService.saveMsEntry(msDto);
    }

    @DeleteMapping("/deletemsentry/{id}")
    void deleteMsEntry(@PathVariable int id){
        msService.deleteMsEntryById(id);
    }
}
