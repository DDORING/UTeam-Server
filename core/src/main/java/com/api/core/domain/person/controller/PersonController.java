package com.api.core.domain.person.controller;

import com.api.core.domain.person.dto.PersonDto;
import com.api.core.domain.person.entity.Person;
import com.api.core.domain.person.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "사용자가 선택한 인물(연예인 등) 관련 API")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/upload/{memberId}")
    @Operation(summary = "인물 생성 및 수정 API", description = "인물의 이름 및 이미지 생성")
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

    // 인물 조회
    @GetMapping("/get/{memberId}")
    @Operation(summary = "인물 조회 API", description = "인물의 이름 및 이미지 조회")
    public ResponseEntity<PersonDto> getFavoritePerson(@PathVariable Long memberId) {
        return personService.findPerson(memberId)
                .map(person -> new PersonDto(person.getName(), person.getPhoto()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}