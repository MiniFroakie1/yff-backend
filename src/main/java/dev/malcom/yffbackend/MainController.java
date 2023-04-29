package dev.malcom.yffbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<>(service.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable UUID id) {
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Map<String, String> payload) {
        if(!service.existsUserEmailOrPassword(payload.get("email"), payload.get("name"))) {
            return new ResponseEntity<>(service.addNewUser(payload.get("name"), payload.get("email"), payload.get("password")), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>("Email/name is already in use", HttpStatus.CONFLICT);
        }
    }
}
