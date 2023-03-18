package com.undina.mainserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Product> products;

    @Column(name = "date_of_end", nullable = false)
    private LocalDate dateOfEnd;

    @Column(name = "percent", nullable = false)
    private Integer percent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id.equals(discount.id) && dateOfEnd.equals(discount.dateOfEnd) && percent.equals(discount.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfEnd, percent);
    }
}
