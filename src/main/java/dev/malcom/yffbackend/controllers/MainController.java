package dev.malcom.yffbackend.controllers;

import dev.malcom.yffbackend.User;
import dev.malcom.yffbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class MainController {
    @Autowired
    private UserService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<>(service.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<UUID[]> login(@RequestBody Map<String, String> payload, @RequestHeader Map<String, String> headers) {
        System.out.println(headers.entrySet());
        Optional<User> user = service.findUserByEmail(payload.get("email"));
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(payload.get("password"));
        if(passwordEncoder.matches(payload.get("password"), user.get().getPassword())) {
            return new ResponseEntity<>(new UUID[] { user.get().getId() }, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<UUID[]> addNewUser (@RequestBody Map<String, String> payload) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = passwordEncoder.encode(payload.get("password"));
        User u = service.addNewUser(payload.get("name"), payload.get("email"), password);
        return new ResponseEntity<>(new UUID[] { u.getId() }, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable UUID id) {
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }



//    @PostMapping
//    public ResponseEntity<?> addNewUser(@RequestBody Map<String, String> payload) {
//        if(!service.existsUserEmailOrPassword(payload.get("email"), payload.get("name"))) {
//            return new ResponseEntity<>(service.addNewUser(payload.get("name"), payload.get("email"), payload.get("password")), HttpStatus.OK);
//        } else  {
//            return new ResponseEntity<>("Email/name is already in use", HttpStatus.CONFLICT);
//        }
//    }
}
