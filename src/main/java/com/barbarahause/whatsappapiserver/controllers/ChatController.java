package com.barbarahause.whatsappapiserver.controllers;

import com.barbarahause.whatsappapiserver.models.MessageType;
import com.barbarahause.whatsappapiserver.requests.MessageRequest;
import com.barbarahause.whatsappapiserver.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Tag(name = "Chat")
public class ChatController {
    private final MessageService messageService;

    @Operation(summary = "you can use this End point to send text, attachment and video messages!" +
            "You can select files anywhere in you local server and send it")
    @MessageMapping("/send")
    @RequestMapping(method = RequestMethod.POST, value = "/send", consumes = "multipart/form-data")
    public String sendMessage(String content, String sender, String receiver, MessageType messageType,
                              @RequestParam("file") Optional<MultipartFile> media) throws IOException {
        return messageService.sendMessage(content, sender, receiver, messageType, media.orElse(null));
    }


}
