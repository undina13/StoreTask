package com.undina.message.model;

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
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin", nullable = false)
    private String admin;

    @Column(name = "user", nullable = false)
    private String user;

    @Column(name = "text", nullable = false)
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) && admin.equals(message.admin) && user.equals(message.user) && text
                .equals(message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, admin, user, text);
    }
}

