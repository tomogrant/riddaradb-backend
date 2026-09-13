package com.se.riddaradb.ms;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class MsController {

    final MsService msService;

    public MsController(MsService msService) {
        this.msService = msService;
    }

    @GetMapping("/ms/getmsentries")
    Collection<MsDto> getMsEntries(){
        return msService.getMsEntries();
    }

    @GetMapping("/ms/getmsentrybyid/{id}")
    MsDto getMsEntryById(@PathVariable int id){
        return msService.getMsEntryById(id);
    }

    @GetMapping("/ms/getmsentriesbyrepoid/{id}")
    Collection<MsDto> getMsEntryByRepoId(@PathVariable Integer id){
        return msService.findByRepoId(id);
    }

    @PostMapping("/ms/postmsentry")
    MsDto postMsEntry(@RequestBody MsDto msDto){
        return msService.saveMsEntry(msDto);
    }

    @PutMapping("/ms/putmsentry")
    MsDto putMsEntry(@RequestBody MsDto msDto){
        return msService.updateMsEntry(msDto);
    }

    @DeleteMapping("/ms/deletemsentry/{id}")
    void deleteMsEntry(@PathVariable int id){
        msService.deleteMsEntryById(id);
    }
}
