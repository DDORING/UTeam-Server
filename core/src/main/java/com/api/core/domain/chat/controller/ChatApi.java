package com.api.core.domain.chat.controller;

import com.api.core.domain.chat.dto.request.ChatMessageRequest;
import com.api.core.domain.chat.dto.response.ChatMessageResponse;
import com.api.core.domain.chat.dto.response.ChatSaveRoomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ChatApi {
    @Operation(
            summary = "채팅방 생성",
            description = "채팅방을 생성합니다.",
            security = {@SecurityRequirement(name = "access_token")},
            tags = {"채팅방"}
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    ChatSaveRoomResponse saveChatRoom();

    @Operation(
            summary = "채팅 메세지 조회",
            description = "채팅방의 메세지 이력을 조회합니다.",
            security = {@SecurityRequirement(name = "access_token")},
            tags = {"채팅 메세지"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청에 성공하였습니다."
    )
    List<ChatMessageResponse> getChatMessages(
            @Parameter(in = ParameterIn.PATH, description = "채팅방 ID", required = true)
            Long roomId
    );

    @Operation(
            summary = "[인간] 채팅 메세지 저장",
            description = "사용자가 보낸 채팅 메세지를 저장합니다.",
            security = {@SecurityRequirement(name = "access_token")},
            tags = {"채팅 메세지"}
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    void saveHumanChatMessage(
            @Parameter(in = ParameterIn.PATH, description = "채팅방 ID", required = true)
            Long roomId,

            @RequestBody ChatMessageRequest request
    );

    @Operation(
            summary = "[AI] 채팅 메세지 저장",
            description = "ChatGPT OPENAI가 보낸 채팅 메세지를 저장합니다.",
            security = {@SecurityRequirement(name = "access_token")},
            tags = {"채팅 메세지"}
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    void saveAIChatMessage(
            @Parameter(in = ParameterIn.PATH, description = "채팅방 ID", required = true)
            Long roomId,

            @RequestBody ChatMessageRequest request
    );




}
