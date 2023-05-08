package dev.malcom.yffbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmailOrName(String email, String name);
    void deleteUserById(UUID id);
}
