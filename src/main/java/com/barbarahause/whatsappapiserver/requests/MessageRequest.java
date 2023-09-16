package com.barbarahause.whatsappapiserver.requests;

import com.barbarahause.whatsappapiserver.models.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String content;
    private Sender sender;
    private Receiver receiver;
    private MessageType messageType;

}
