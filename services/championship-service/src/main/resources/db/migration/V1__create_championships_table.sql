CREATE TABLE championships (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    promotion VARCHAR(50) NOT NULL,
    division VARCHAR(50) NOT NULL,
    belt_image_url VARCHAR(500) NOT NULL,
    logo_image_url VARCHAR(500),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    prestige_level INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);