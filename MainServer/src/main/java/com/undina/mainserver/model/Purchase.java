package com.undina.mainserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_purchase", nullable = false)
    private LocalDateTime datePurchase;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id.equals(purchase.id) && datePurchase.equals(purchase.datePurchase) && user
                .equals(purchase.user) && product
                .equals(purchase.product) && price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datePurchase, user, product, price);
    }
}
