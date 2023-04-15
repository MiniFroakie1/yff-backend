package dev.malcom.yffbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserById(int id) {
        return repository.findUserById(id);
    }

    public User addNewUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        repository.save(user);
        return user;
    }

    public boolean existsUserEmail(String email) {
        return repository.existsUserByEmail(email);
    }
}
