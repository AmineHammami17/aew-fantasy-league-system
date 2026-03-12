CREATE TABLE wrestler_tag_teams (
    id UUID PRIMARY KEY,
    wrestler_id UUID NOT NULL,
    tag_team_id UUID NOT NULL,
    tag_team_name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_tagteam_wrestler FOREIGN KEY (wrestler_id) REFERENCES wrestlers(id)
        ON DELETE CASCADE
);