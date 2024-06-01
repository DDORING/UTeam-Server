package com.api.core.domain.person.repository;

import com.api.core.domain.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // 회원 ID로 Person 엔티티를 찾음
    Optional<Person> findByMemberId(Long memberId);
}
