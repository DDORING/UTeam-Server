package com.api.core.domain.chat.service;

import com.api.core.domain.chat.dto.response.ChatSaveRoomResponse;
import com.api.core.domain.chat.entity.ChatRoom;
import com.api.core.domain.chat.repository.ChatRoomRepository;
import com.api.core.domain.member.entity.Member;
import com.api.core.domain.person.entity.Person;
import com.api.core.domain.person.repository.PersonRepository;
import com.api.core.global.oauth2.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final AuthService authService;
    private final ChatRoomRepository chatRoomRepository;
    private final PersonRepository personRepository;

    @Transactional
    public ChatSaveRoomResponse saveDebateRoom() {
        final Member member = authService.getMember();
        Long id = chatRoomRepository.save(ChatRoom.builder()
                        .member(member)
                        .person(getPerson((member)))
                        .build()).getId();
        return new ChatSaveRoomResponse(id);
    }

    private Person getPerson(Member member) {
        return personRepository.findByMemberId(member.getId()).orElseThrow(EntityNotFoundException::new);
    }
}
