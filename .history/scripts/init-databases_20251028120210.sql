-- Database initialization script for AEW Fantasy League
-- This script creates all the necessary databases and users for the microservices

-- Create databases
CREATE DATABASE auth_db;
CREATE DATABASE user_db;
CREATE DATABASE wrestler_db;
CREATE DATABASE team_db;
CREATE DATABASE league_db;
CREATE DATABASE match_db;
CREATE DATABASE scoring_db;
CREATE DATABASE leaderboard_db;
CREATE DATABASE notification_db;

-- Create users and grant permissions
CREATE USER auth_user WITH PASSWORD 'auth_password';
CREATE USER user_user WITH PASSWORD 'user_password';
CREATE USER wrestler_user WITH PASSWORD 'wrestler_password';
CREATE USER team_user WITH PASSWORD 'team_password';
CREATE USER league_user WITH PASSWORD 'league_password';
CREATE USER match_user WITH PASSWORD 'match_password';
CREATE USER scoring_user WITH PASSWORD 'scoring_password';
CREATE USER leaderboard_user WITH PASSWORD 'leaderboard_password';
CREATE USER notification_user WITH PASSWORD 'notification_password';

-- Grant permissions
GRANT ALL PRIVILEGES ON DATABASE auth_db TO auth_user;
GRANT ALL PRIVILEGES ON DATABASE user_db TO user_user;
GRANT ALL PRIVILEGES ON DATABASE wrestler_db TO wrestler_user;
GRANT ALL PRIVILEGES ON DATABASE team_db TO team_user;
GRANT ALL PRIVILEGES ON DATABASE league_db TO league_user;
GRANT ALL PRIVILEGES ON DATABASE match_db TO match_user;
GRANT ALL PRIVILEGES ON DATABASE scoring_db TO scoring_user;
GRANT ALL PRIVILEGES ON DATABASE leaderboard_db TO leaderboard_user;
GRANT ALL PRIVILEGES ON DATABASE notification_db TO notification_user;
