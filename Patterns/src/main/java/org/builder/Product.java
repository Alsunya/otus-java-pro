package org.builder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Product {
    private final int id;
    private final String title;
    private final String description;
    private final double cost;
    private final double weight;
    private final double width;
    private final double length;
    private final double height;

    public Product(Builder builder) {
        id = builder.id;
        title = builder.title;
        description = builder.description;
        cost = builder.cost;
        weight = builder.weight;
        width = builder.width;
        length = builder.length;
        height = builder.height;
    }

    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private double cost;
        private double weight;
        private double width;
        private double length;
        private double height;

        public Product build() {
            return new Product(this);
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder width(double width) {
            this.width = width;
            return this;
        }

        public Builder length(double length) {
            this.length = length;
            return this;
        }

        public Builder height(double height) {
            this.height = height;
            return this;
        }
    }
}
