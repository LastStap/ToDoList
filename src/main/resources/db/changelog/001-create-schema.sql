--liquibase formatted sql
--changeset daniil.dumshenko:1

CREATE TABLE users (
                       id UUID NOT NULL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE tasks (
                      id UUID NOT NULL PRIMARY KEY,
                      user_id UUID NOT NULL,
                      title VARCHAR(100) NOT NULL,
                      description TEXT,
                      status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                      priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
                      due_date TIMESTAMP,
                      created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                      CONSTRAINT fk_tasks_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE categories (
                          id UUID NOT NULL PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          description TEXT,
                          user_id UUID NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          CONSTRAINT fk_categories_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE tasks_categories (
                               id UUID NOT NULL PRIMARY KEY,
                               task_id UUID NOT NULL,
                               category_id UUID NOT NULL,
                               CONSTRAINT fk_categories_tasks_tasks FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE,
                               CONSTRAINT fk_categories_tasks_categories FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE subtasks (
                         id UUID NOT NULL PRIMARY KEY,
                         task_id UUID NOT NULL,
                         title VARCHAR(100) NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         CONSTRAINT fk_subtasks_tasks FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);

CREATE TABLE comments (
                         id UUID NOT NULL PRIMARY KEY,
                         task_id UUID NOT NULL,
                         user_id UUID NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         CONSTRAINT fk_comments_tasks FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE,
                         CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE attachments (
                            id UUID NOT NULL PRIMARY KEY,
                            task_id UUID NOT NULL,
                            file_name VARCHAR(255) NOT NULL,
                            file_path VARCHAR(500) NOT NULL,
                            file_type VARCHAR(50),  -- Example: "image/png", "application/pdf"
                            file_size BIGINT,       -- File size in bytes
                            uploaded_at TIMESTAMP NOT NULL DEFAULT NOW(),
                            CONSTRAINT fk_attachments_tasks FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);

-- Indexes for better performance
CREATE INDEX idx_tasks_users ON tasks(user_id);
CREATE INDEX idx_categories_users ON categories(user_id);
CREATE INDEX idx_comments_users ON comments(user_id);
