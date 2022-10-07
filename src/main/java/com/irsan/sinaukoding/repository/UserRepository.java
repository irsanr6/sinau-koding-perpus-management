package com.irsan.sinaukoding.repository;

import com.irsan.sinaukoding.entity.UserPerpus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPerpus, Long> {

    Optional<UserPerpus> findByUsernameOrEmail(String username, String email);
    Optional<UserPerpus> findByUsername(String username);
    Optional<UserPerpus> findByEmail(String email);

}
