package dev.malcom.yffbackend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findUserById(UUID id);
    boolean existsUserByEmailOrName(String email, String name);
    void deleteUserById(UUID id);
}
