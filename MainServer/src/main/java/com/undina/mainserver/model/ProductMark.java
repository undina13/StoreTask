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
@Table(name = "product_marks")
public class ProductMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark", nullable = false)
    private Integer mark;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMark that = (ProductMark) o;
        return id.equals(that.id) && mark.equals(that.mark) && product.equals(that.product) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, product, user);
    }
}
