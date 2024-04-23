CREATE TABLE comments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          task_id INT NOT NULL,
                          user_id INT NOT NULL,
                          comment_text TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (task_id) REFERENCES tasks(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);