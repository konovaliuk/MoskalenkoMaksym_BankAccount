package main.java.models;

import main.java.types.ProfileRole;

import java.sql.Timestamp;
import java.util.UUID;

public class Profile {
    private UUID id;
    private String login;
    private String passwordHash;
    private ProfileRole role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Profile(String login, String passwordHash) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = ProfileRole.User;
    }

    public Profile(UUID id, String login, String passwordHash, ProfileRole role) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Profile() {
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
