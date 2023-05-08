package dev.malcom.yffbackend;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 30)
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
