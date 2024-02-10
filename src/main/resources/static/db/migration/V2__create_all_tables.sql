CREATE TABLE tasks
(
    id                  INT PRIMARY KEY AUTO_INCREMENT,
    task_name           VARCHAR(255) NOT NULL,
    description         TEXT,
    project_id          INT,
    created_by_user_id  INT,
    assigned_to_user_id INT,
    priority            INT,
    deadline            TIMESTAMP,
    status              VARCHAR(20) DEFAULT 'Open',
    created_at          TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (created_by_user_id) REFERENCES users (id),
    FOREIGN KEY (assigned_to_user_id) REFERENCES users (id)
);

CREATE TABLE attachments
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    task_id    INT,
    file_name  VARCHAR(255) NOT NULL,
    file_type  VARCHAR(50)  NOT NULL,
    file_data  LONGBLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks (id)
);

CREATE TABLE history
(
    id                 INT PRIMARY KEY AUTO_INCREMENT,
    user_id            INT,
    action_description TEXT,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);