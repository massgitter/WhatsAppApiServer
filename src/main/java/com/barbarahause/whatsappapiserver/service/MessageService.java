package com.barbarahause.whatsappapiserver.service;

import com.barbarahause.whatsappapiserver.models.ChatRoom;
import com.barbarahause.whatsappapiserver.models.Message;
import com.barbarahause.whatsappapiserver.models.MessageType;
import com.barbarahause.whatsappapiserver.models.Users;
import com.barbarahause.whatsappapiserver.repo.ChatRoomRepo;
import com.barbarahause.whatsappapiserver.repo.MessageRepository;
import com.barbarahause.whatsappapiserver.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UsersRepo usersRepo;
    private final ChatRoomRepo chatRoomRepo;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public String sendMessage(String content, String senders, String receivers, MessageType messageType,
                              MultipartFile media) throws IOException {
        Users sender = usersRepo.findByUserName(senders);
        Users receiver = usersRepo.findByUserName(receivers);

        if (Objects.isNull(sender))
            throw new RuntimeException("Sender not available");
        else if (Objects.isNull(receiver)) throw new RuntimeException("Receiver is not available");

        else {

            int members = (int) chatRoomRepo.findAll().stream().distinct().map(ChatRoom::getMembers).count();
            if (members <= 500) {
                Message message = new Message();
                if (messageType.getCode().equals("TEXT_MESSAGE") || Objects.equals(messageType.getCode(), "ATTACHMENT_MESSAGE")) {
                    message = Message.builder()
                            .content(content)
                            .messageType(messageType.getCode())
                            .sender(sender)
                            .receiver(receiver)
                            .build();
                    if (Objects.nonNull(media)) message.setData(media.getBytes());

                }else if (Objects.equals(messageType.getCode(), "VIDEO_MESSAGE")){
                    message = Message.builder()
                            .content(content)
                            .sender(sender)
                            .receiver(receiver)
                            .data(media.getBytes())
                            .messageType(messageType.getCode())
                            .build();
                }

                ChatRoom room = chatRoomRepo.findChatRoomByUsers(sender);
                if (Objects.isNull(room)) {
                    ChatRoom chatRoom = ChatRoom.builder()
                            .members(members + 1)
                            .users(usersRepo.findByUserName(senders))
                            .build();
                    chatRoomRepo.save(chatRoom);
                }

                ChatRoom room1 = chatRoomRepo.findChatRoomByUsers(receiver);
                if (Objects.isNull(room1)) {
                    ChatRoom chatRoom1 = ChatRoom.builder()
                            .members(members + 1)
                            .users(usersRepo.findByUserName(receivers))
                            .build();
                    chatRoomRepo.save(chatRoom1);
                }

                message = messageRepository.save(message);
                messagingTemplate.convertAndSendToUser(receivers, "/queue/messages", message);
                return content;
            }

        }

        return null;
    }

}
