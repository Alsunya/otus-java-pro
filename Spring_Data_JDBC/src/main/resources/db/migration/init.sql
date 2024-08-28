CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255)
);

INSERT INTO users(name, email) VALUES ('Александр', 'alex@example.com');
INSERT INTO users(name, email) VALUES ('Дарья', 'dariya@example.com');