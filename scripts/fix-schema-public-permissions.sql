-- Run this ONCE against your EXISTING Postgres to fix "permission denied for schema public".
-- Best method on Windows: run scripts/fix-schema-public-permissions.ps1
-- Or manually: Get-Content scripts/fix-schema-public-permissions.sql | docker exec -i aew-postgres psql -U postgres -v ON_ERROR_STOP=1

-- Make each app user the OWNER of schema public (PostgreSQL 15+ requirement for Flyway)
\c auth_db postgres
ALTER SCHEMA public OWNER TO auth_user;

\c user_db postgres
ALTER SCHEMA public OWNER TO user_user;

\c wrestler_db postgres
ALTER SCHEMA public OWNER TO wrestler_user;

\c team_db postgres
ALTER SCHEMA public OWNER TO team_user;

\c league_db postgres
ALTER SCHEMA public OWNER TO league_user;

\c match_db postgres
ALTER SCHEMA public OWNER TO match_user;

\c scoring_db postgres
ALTER SCHEMA public OWNER TO scoring_user;

\c leaderboard_db postgres
ALTER SCHEMA public OWNER TO leaderboard_user;

\c notification_db postgres
ALTER SCHEMA public OWNER TO notification_user;
