package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import types.ProfileRole;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "role")
    private String role;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Profile(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = ProfileRole.User.toSqlName();
    }

    public Profile(Long id, String login, String passwordHash, String role) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
