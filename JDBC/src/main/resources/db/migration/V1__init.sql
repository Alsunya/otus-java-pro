CREATE TABLE IF NOT EXISTS products(
id int AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
price INT NOT NULL
);

INSERT INTO products (title, price) VALUES
('Product 1', 10),
('Product 2', 20),
('Product 3', 30);