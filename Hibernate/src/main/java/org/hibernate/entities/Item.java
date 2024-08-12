package org.hibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private double price;

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private Set<Purchase> purchases;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        purchases.forEach(purchase -> builder
                .append(purchase.getCustomer().getName())
                .append("\n"));
        return new StringBuilder()
                .append("Item{")
                .append("id=")
                .append(id)
                .append(", title='")
                .append(title)
                .append('\'')
                .append(", price=")
                .append(price)
                .append(", customers=")
                .append(builder)
                .append('}')
                .toString();
    }
}
