package com.undina.mainserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "balance")
    private Integer balance;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_frozen")
    private  boolean isFrozen;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isFrozen == user.isFrozen && id.equals(user.id) && username.equals(user.username) && email
                .equals(user.email) && login.equals(user.login) && balance.equals(user.balance) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, login, balance, role, isFrozen);
    }
}
