package com.undina.mainserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private Organization organization;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "count")
    private Integer count;

    @ManyToMany
    private List<Discount> discounts;

    @ManyToMany
    private List<KeyWord> keyWords;

    @ManyToMany
    private List<Characteristic> characteristics;

    @OneToMany
    private List<ProductReview> reviews;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isAvailable == product.isAvailable && id.equals(product.id) && name.equals(product.name) && description
                .equals(product.description) && organization.equals(product.organization) && price
                .equals(product.price) && count.equals(product.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, organization, price, count, isAvailable);
    }
}