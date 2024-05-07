CREATE TABLE task_history
(
    id        SERIAL PRIMARY KEY,
    task_id   INTEGER      NOT NULL,
    user_id   INTEGER      NOT NULL,
    action    VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    details   TEXT,
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);