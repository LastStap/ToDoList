--liquibase formatted sql
--changeset daniil.dumshenko:3

INSERT INTO tasks (id, user_id, title, description, status, priority, due_date, created_at) VALUES
    ('b3f5c2d4-0e41-4b68-8f3a-1e2f4b7c8a9d', '4542d77c-e970-4c99-9c97-76eb0dd07969', 'Complete project proposal', 'Draft and submit the initial project proposal', 'PENDING', 'HIGH', '2025-01-13T15:54:32Z', '2025-01-06T15:54:32Z'),
    ('d7e1a6b2-5c3d-4f92-9f1a-2e6a7b8c9d0e', '4542d77c-e970-4c99-9c97-76eb0dd07969', 'Team meeting', 'Discuss project milestones with the team', 'IN_PROGRESS', 'MEDIUM', '2025-01-13T15:54:32Z', '2025-01-06T15:54:32Z'),
    ('a4c7e9d3-1b25-4f80-8c7a-3d9e5b6a2f1d', '4542d77c-e970-4c99-9c97-76eb0dd07969', 'Code review', 'Review pull requests and provide feedback', 'PENDING', 'HIGH', '2025-01-13T15:54:32Z', '2025-01-06T15:54:32Z'),
    ('f2b8d6e4-9a1c-4f70-8d2a-5c3e7b9d0a1f', '4542d77c-e970-4c99-9c97-76eb0dd07969', 'Update documentation', 'Ensure all API endpoints are properly documented', 'COMPLETED', 'LOW', '2025-01-13T15:54:32Z', '2025-01-06T15:54:32Z'),
    ('c5d9e7a3-2b40-4f91-9c8a-7b6e3d1f2a5d', '4542d77c-e970-4c99-9c97-76eb0dd07969', 'Prepare presentation', 'Create slides for the stakeholder meeting', 'PENDING', 'MEDIUM', '2025-01-13T15:54:32Z', '2025-01-06T15:54:32Z');
