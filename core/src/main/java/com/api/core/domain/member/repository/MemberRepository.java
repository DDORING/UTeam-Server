package com.api.core.domain.member.repository;

import com.api.core.domain.member.entity.Member;
import com.api.core.domain.member.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndOauthId(SocialType socialType, String oauthId);

    boolean existsMemberByNickname(String nickname);
}