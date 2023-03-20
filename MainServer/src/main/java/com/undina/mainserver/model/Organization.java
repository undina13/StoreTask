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
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "logo", nullable = false)
    private  String logo;

    @OneToMany
    private List<Product> products;

    @ManyToOne
    private User owner;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_frozen")
    private boolean isFrozen;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return isDeleted == that.isDeleted && isFrozen == that.isFrozen && id.equals(that.id) && name
                .equals(that.name) && description.equals(that.description) && logo.equals(that.logo) && owner
                .equals(that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, logo, owner, isDeleted, isFrozen);
    }
}
