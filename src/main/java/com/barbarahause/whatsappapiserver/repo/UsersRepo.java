package com.barbarahause.whatsappapiserver.repo;

import com.barbarahause.whatsappapiserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUserName(String username);
}
