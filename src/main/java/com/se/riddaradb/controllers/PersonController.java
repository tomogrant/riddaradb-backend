package com.se.riddaradb.controllers;

import com.se.riddaradb.dtos.BibDto;
import com.se.riddaradb.dtos.PersonDto;
import com.se.riddaradb.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PersonController {

    final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getpersonentries")
    Collection<PersonDto> getPersonEntry(){
        return personService.getPersonEntries();
    }

    @GetMapping("/getpersonentrybyid/{id}")
    PersonDto getPersonEntryById(@PathVariable int id){
        return personService.getPersonEntryById(id);
    }

    @PostMapping("/postpersonentry")
    PersonDto postPersonEntry(@RequestBody PersonDto personDto){
        return personService.savePersonEntry(personDto);
    }

    @PutMapping("/putpersonentry")
    PersonDto putPersonEntry(@RequestBody PersonDto personDto){
        return personService.savePersonEntry(personDto);
    }

    @DeleteMapping("/deletepersonentry/{id}")
    void deletePersonEntry(@PathVariable int id){
        personService.deletePersonEntryById(id);
    }
}
