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
@Table(name = "key_words")
public class KeyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word", nullable = false)
    private String word;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyWord keyWord = (KeyWord) o;
        return id.equals(keyWord.id) && word.equals(keyWord.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }
}
