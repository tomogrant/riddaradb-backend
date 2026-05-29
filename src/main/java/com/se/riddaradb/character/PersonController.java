package com.se.riddaradb.character;

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
