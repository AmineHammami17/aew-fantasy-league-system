CREATE TABLE title_reigns (
    id UUID PRIMARY KEY,

    championship_id UUID NOT NULL,

    wrestler_id UUID NOT NULL,

    start_date DATE NOT NULL,
    end_date DATE,

    is_current BOOLEAN NOT NULL,

    defense_count INTEGER NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_title_reign_championship
        FOREIGN KEY (championship_id)
        REFERENCES championships(id)
        ON DELETE CASCADE
);