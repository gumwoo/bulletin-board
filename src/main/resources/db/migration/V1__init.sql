CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    content TEXT,
    author VARCHAR(50) NOT NULL,
    view_count BIGINT DEFAULT 0
);
