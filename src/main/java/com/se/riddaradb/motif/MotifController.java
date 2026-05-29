package com.se.riddaradb.motif;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MotifController {

    final MotifService motifService;

    public MotifController(MotifService motifService) {
        this.motifService = motifService;
    }

    @GetMapping("/motifs/getmotifs")
    Collection<MotifDto> getMotifEntries(){
        return motifService.getMotifEntries();
    }

    @GetMapping("/motifs/getmotifbyid/{id}")
    MotifDto getMotifEntryById(@PathVariable int id){
        return motifService.getMotifEntryById(id);
    }

    @GetMapping("/motifs/getrootmotifs")
    Collection<MotifDto> getRootMotifs(){
        return motifService.getRootMotifs();
    }

    @GetMapping("/motifs/getchildmotifsbyid/{id}")
    Collection<MotifDto> getChildMotifsById(@PathVariable int id){
        return motifService.getChildMotifs(id);
    }

    @PostMapping("/motifs/postmotif")
    MotifDto postMotifEntry(@RequestBody MotifDto motifDto){
        return motifService.saveMotifEntry(motifDto);
    }

    @PutMapping("/motifs/putmotif")
    MotifDto putMotifEntry(@RequestBody MotifDto motifDto){
        return motifService.saveMotifEntry(motifDto);
    }

    @DeleteMapping("/motifs/deletemotif/{id}")
    void deleteMotifEntry(@PathVariable int id){
        motifService.deleteMotifEntryById(id);
    }
}
