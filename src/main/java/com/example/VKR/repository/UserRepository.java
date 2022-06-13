package com.example.VKR.repository;

import com.example.VKR.model.ERole;
import com.example.VKR.model.Role;
import com.example.VKR.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByRoles(ERole ERole);

    Boolean existsByUsername(String username);
}
