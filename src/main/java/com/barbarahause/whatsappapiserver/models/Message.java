package com.barbarahause.whatsappapiserver.models;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Message extends Common{
    private String content;
    @ManyToOne
    private Users sender;
    @ManyToOne
    private Users receiver;

    private String messageType;

    @Lob
    private byte[] data;
}
