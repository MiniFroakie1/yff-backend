package dev.malcom.yffbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserById(UUID id) {
        return repository.findUserById(id);
    }

    public User addNewUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        repository.save(user);
        return user;
    }

    public boolean existsUserEmailOrPassword(String email, String name) {
        return repository.existsUserByEmailOrName(email, name);
    }
}
