package com.se.riddaradb.ms;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MsRepositoryController {

    final MsRepositoryService msRepositoryService;

    public MsRepositoryController(MsRepositoryService msRepositoryService) {
        this.msRepositoryService = msRepositoryService;
    }

    @GetMapping("/ms/getmsrepositories")
    Collection<MsRepositoryDto> getMsEntries(){
        return msRepositoryService.getMsRepositories();
    }

    @GetMapping("/ms/getmsrepositorybyid/{id}")
    MsRepositoryDto getMsEntryById(@PathVariable int id){
        return msRepositoryService.getMsRepositoryById(id);
    }

    @PostMapping("/ms/postmsrepository")
    MsRepositoryDto postMsEntry(@RequestBody MsRepositoryDto msRepositoryDto){
        return msRepositoryService.saveMsRepository(msRepositoryDto);
    }

    @PutMapping("/ms/putmsrepository")
    MsRepositoryDto putMsEntry(@RequestBody MsRepositoryDto msRepositoryDto){
        return msRepositoryService.updateMsRepository(msRepositoryDto);
    }

    @DeleteMapping("/ms/deletemsrepository/{id}")
    void deleteMsEntry(@PathVariable int id){
        msRepositoryService.deleteMsRepositoryById(id);
    }
}