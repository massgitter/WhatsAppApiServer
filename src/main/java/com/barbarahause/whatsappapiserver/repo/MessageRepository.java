package com.barbarahause.whatsappapiserver.repo;

import com.barbarahause.whatsappapiserver.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
