package com.api.core.domain.chat.service;

import com.api.core.domain.chat.dto.request.ChatMessageRequest;
import com.api.core.domain.chat.dto.response.ChatMessageResponse;
import com.api.core.domain.chat.entity.ChatMessage;
import com.api.core.domain.chat.entity.ChatRoom;
import com.api.core.domain.chat.entity.SenderType;
import com.api.core.domain.chat.repository.ChatMessageRepository;
import com.api.core.domain.chat.repository.ChatRoomRepository;
import com.api.core.domain.member.entity.Member;
import com.api.core.domain.person.entity.Person;
import com.api.core.domain.person.repository.PersonRepository;
import com.api.core.global.oauth2.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final AuthService authService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PersonRepository personRepository;

    @Transactional
    public void saveHumanChatMessage(final Long roomId, ChatMessageRequest request) {
        chatMessageRepository.save(request.toHumanMessageEntity(getDebateRoom(roomId)));
    }

    @Transactional
    public void saveAIChatMessage(Long roomId, ChatMessageRequest request) {
        chatMessageRepository.save(request.toAIMessageEntity(getDebateRoom(roomId)));
    }

    private ChatRoom getDebateRoom(final Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);
    }

    public StringBuilder getAllMessages(Long roomId) {
        StringBuilder sb = new StringBuilder();
        getChatMessages(roomId)
                .forEach(message -> sb.append(message.getMessage()));
        return sb;
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponse> getChatMessages(Long roomId) {
        Member member = authService.getMember();
        Person person = personRepository.findByMemberId(member.getId()).orElseThrow(EntityNotFoundException::new);

        List<ChatMessage> allChatMessages = chatMessageRepository.findAllByChatRoomId(roomId);
        return allChatMessages.stream().map(message -> {
            if (message.getSenderType().equals(SenderType.AI)) {
                return ChatMessageResponse.builder()
                        .name(person.getName()).imageUrl(person.getPhoto()).build();
            }
            return ChatMessageResponse.builder().name(member.getNickname()).imageUrl(member.getImageUrl()).build();
        }).collect(Collectors.toList());
    }

}
