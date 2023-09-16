package com.barbarahause.whatsappapiserver.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
    private String token;
    private Long id;
    private String type = "Bearer";
    private String userName;
    private String email;

    public UsersResponse(String accessToken, Long id, String userName, String email) {
        this.token = accessToken;
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

}
