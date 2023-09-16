package com.barbarahause.whatsappapiserver.service;

import com.barbarahause.whatsappapiserver.models.Users;
import com.barbarahause.whatsappapiserver.repo.UsersRepo;
import com.barbarahause.whatsappapiserver.requests.UserRequest;
import com.barbarahause.whatsappapiserver.responses.UsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
   private final PasswordEncoder passwordEncoder;
    private final UsersRepo usersRepo;

    public UsersResponse saveUser(UserRequest request) {
        Users users = Users.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return usersRepo.save(users).response();
    }
}
