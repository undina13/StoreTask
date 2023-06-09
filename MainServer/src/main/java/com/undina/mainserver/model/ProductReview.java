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
@Table(name = "product_reviews")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review", nullable = false)
    private String review;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReview that = (ProductReview) o;
        return id.equals(that.id) && review.equals(that.review) && product.equals(that.product) && user
                .equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, review, product, user);
    }
}
