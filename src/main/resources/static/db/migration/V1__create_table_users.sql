CREATE TABLE products
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    description  TEXT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE projects
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(100) NOT NULL,
    product_id   INT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE users
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    login      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(255)       NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    roles      VARCHAR(20)        NOT NULL,
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE SET NULL
);

INSERT INTO products (product_name, description) VALUES ('Product 1', 'Description for Product 1');

INSERT INTO projects (project_name, product_id) VALUES ('Project 1', 1);