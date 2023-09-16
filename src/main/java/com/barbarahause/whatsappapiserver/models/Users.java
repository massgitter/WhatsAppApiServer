package com.barbarahause.whatsappapiserver.models;

import com.barbarahause.whatsappapiserver.responses.UsersResponse;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Users extends Common{
    @NotBlank
    @Column(unique = true)
    private String userName;

    private String email;

    @NotBlank
    private String password;

    public UsersResponse response() {
        return UsersResponse.builder()
                .userName(userName)
                .email(email)
                .build();
    }
}
