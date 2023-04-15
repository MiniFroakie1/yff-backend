package dev.malcom.yffbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<Iterable<User>>(service.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable int id) {
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Map<String, String> payload) {
        if(!service.existsUserEmail(payload.get("email"))) {
            return new ResponseEntity<>(service.addNewUser(payload.get("name"), payload.get("email")), HttpStatus.OK);
        }
        return new ResponseEntity<>("Email already in use",HttpStatus.CONFLICT);
    }
}
