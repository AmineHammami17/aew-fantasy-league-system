CREATE TABLE finishers (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    wrestler_id UUID NOT NULL,
    CONSTRAINT fk_finisher_wrestler FOREIGN KEY (wrestler_id) REFERENCES wrestlers(id)
        ON DELETE CASCADE
);