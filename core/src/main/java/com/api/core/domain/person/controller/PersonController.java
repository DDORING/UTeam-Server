package com.api.core.domain.person.controller;

import com.api.core.domain.person.dto.PersonDto;
import com.api.core.domain.person.entity.Person;
import com.api.core.domain.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/upload/{memberId}")
    public ResponseEntity<PersonDto> uploadPersonInfo(
            @PathVariable Long memberId,
            @RequestParam("name") String name,
            @RequestParam("photo") MultipartFile photo) {
        try {
            Person person = personService.createOrUpdatePerson(memberId, photo, name);
            PersonDto personDto = new PersonDto(person.getName(), person.getPhoto());
            return ResponseEntity.ok(personDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}