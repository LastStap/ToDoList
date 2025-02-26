--liquibase formatted sql
--changeset daniil.dumshenko:1

CREATE TABLE Users (
                       id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                       updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE Task (
                      id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                      user_id UUID NOT NULL,
                      title VARCHAR(100) NOT NULL,
                      description TEXT,
                      status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                      priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
                      due_date TIMESTAMP,
                      created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                      updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                      CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE
);

CREATE TABLE Category (
                          id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          description TEXT,
                          user_id UUID NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE
);

CREATE TABLE Task_Category (
                               id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                               task_id UUID NOT NULL,
                               category_id UUID NOT NULL,
                               CONSTRAINT fk_task_category_task FOREIGN KEY (task_id) REFERENCES Task (id) ON DELETE CASCADE,
                               CONSTRAINT fk_task_category_category FOREIGN KEY (category_id) REFERENCES Category (id) ON DELETE CASCADE
);

CREATE TABLE Subtask (
                         id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                         task_id UUID NOT NULL,
                         title VARCHAR(100) NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         CONSTRAINT fk_subtask_task FOREIGN KEY (task_id) REFERENCES Task (id) ON DELETE CASCADE
);

CREATE TABLE Comment (
                         id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                         task_id UUID NOT NULL,
                         user_id UUID NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         CONSTRAINT fk_comment_task FOREIGN KEY (task_id) REFERENCES Task (id) ON DELETE CASCADE,
                         CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE
);

CREATE TABLE Attachment (
                            id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
                            task_id UUID NOT NULL,
                            file_name VARCHAR(255) NOT NULL,
                            file_path VARCHAR(500) NOT NULL,
                            file_type VARCHAR(50),  -- Example: "image/png", "application/pdf"
                            file_size BIGINT,       -- File size in bytes
                            uploaded_at TIMESTAMP NOT NULL DEFAULT NOW(),
                            CONSTRAINT fk_attachment_task FOREIGN KEY (task_id) REFERENCES Task (id) ON DELETE CASCADE
);

-- Indexes for better performance
CREATE INDEX idx_task_user ON Task(user_id);
CREATE INDEX idx_category_user ON Category(user_id);
CREATE INDEX idx_comment_user ON Comment(user_id);
