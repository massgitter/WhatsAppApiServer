package com.barbarahause.whatsappapiserver.repo;

import com.barbarahause.whatsappapiserver.models.ChatRoom;
import com.barbarahause.whatsappapiserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, Long> {

    ChatRoom findChatRoomByUsers(Users users);
}
