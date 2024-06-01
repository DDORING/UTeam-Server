package com.api.core.domain.chat.controller;

import com.api.core.domain.chat.dto.request.ChatMessageRequest;
import com.api.core.domain.chat.dto.response.ChatMessageResponse;
import com.api.core.domain.chat.dto.response.ChatSaveRoomResponse;
import com.api.core.domain.chat.service.ChatMessageService;
import com.api.core.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat-rooms")
public class ChatController implements ChatApi {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    @Override
    public ChatSaveRoomResponse saveChatRoom() {
        return chatRoomService.saveDebateRoom();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{roomId}/human")
    @Override
    public void saveHumanChatMessage(@PathVariable final Long roomId, @RequestBody final ChatMessageRequest request) {
        chatMessageService.saveHumanChatMessage(roomId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{roomId}/ai")
    @Override
    public void saveAIChatMessage(@PathVariable final Long roomId, @RequestBody final ChatMessageRequest request) {
        chatMessageService.saveAIChatMessage(roomId, request);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{roomId}")
    @Override
    public List<ChatMessageResponse> getChatMessages(@PathVariable final Long roomId) {
        return chatMessageService.getChatMessages(roomId);
    }


}
