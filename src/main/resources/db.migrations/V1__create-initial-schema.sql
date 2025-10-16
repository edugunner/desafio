CREATE TYPE status_enum AS ENUM ('TODO', 'DOING', 'DONE');
CREATE TYPE priority_enum AS ENUM ('LOW', 'MEDIUM', 'HIGH');

CREATE TABLE project (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date TIMESTAMP,
    end_date TIMESTAMP
);

CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status status_enum,
    priority priority_enum,
    due_date TIMESTAMP,
    project_id BIGINT,

    CONSTRAINT fk_task_project
        FOREIGN KEY (project_id)
        REFERENCES project (id)
);