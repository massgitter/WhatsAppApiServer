package com.barbarahause.whatsappapiserver.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChatRoom extends Common{
    private int members;

    @ManyToOne
    private Users users;

}
