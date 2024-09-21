CREATE SCHEMA IF NOT EXISTS products;

CREATE TABLE IF NOT EXISTS products.product (
id SERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
price DECIMAL NOT NULL
);

INSERT INTO products.product (id, name, price) VALUES
(1, 'Product 1', 10.2),
(2, 'Product 2', 20.9),
(3, 'Product 3', 30.5);