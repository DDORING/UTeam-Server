package com.api.core.domain.person.service;

import com.api.core.domain.member.entity.Member;
import com.api.core.domain.member.repository.MemberRepository;
import com.api.core.domain.person.dto.PersonDto;
import com.api.core.domain.person.entity.Person;
import com.api.core.domain.person.repository.PersonRepository;
import com.api.core.config.S3Service;
import com.api.core.global.oauth2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final MemberRepository memberRepository;
    private final S3Service s3Service;
    private final AuthService authService;

    @Autowired
    public PersonService(PersonRepository personRepository, MemberRepository memberRepository, S3Service s3Service, AuthService authService) {
        this.personRepository = personRepository;
        this.memberRepository = memberRepository;
        this.s3Service = s3Service;
        this.authService = authService;
    }

    public Optional<Person> findPerson() {
        Member member = authService.getMember();
        return personRepository.findByMemberId(member.getId());
    }

    @Transactional
    public Person createOrUpdatePerson(MultipartFile photo, String name) throws IOException {
        Member member = authService.getMember();

        // S3에 이미지 업로드 하고 URL 반환 받음
        String photoUrl = s3Service.uploadFile(photo);

        // 기존 인물 정보가 있는지 확인 후 생성 / 업데이트
        Person person = personRepository.findByMemberId(member.getId())
                .map(p -> {
                    p.update(new PersonDto(name, photoUrl));
                    return p;
                })
                .orElse(Person.builder()
                        .member(member)
                        .name(name)
                        .photo(photoUrl)
                        .build());

        return personRepository.save(person);
    }
}

