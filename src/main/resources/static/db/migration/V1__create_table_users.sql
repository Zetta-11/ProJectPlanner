CREATE TABLE users
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(255)       NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    roles      VARCHAR(20)        NOT NULL
);