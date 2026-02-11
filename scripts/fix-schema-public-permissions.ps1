# Fix "permission denied for schema public" by making each app user the owner of schema public.
# Run from project root with: .\scripts\fix-schema-public-permissions.ps1
# Requires: aew-postgres container running (docker-compose up -d postgres)

$ErrorActionPreference = "Stop"
$dbs = @(
    @{ db = "auth_db"; user = "auth_user" },
    @{ db = "user_db"; user = "user_user" },
    @{ db = "wrestler_db"; user = "wrestler_user" },
    @{ db = "team_db"; user = "team_user" },
    @{ db = "league_db"; user = "league_user" },
    @{ db = "match_db"; user = "match_user" },
    @{ db = "scoring_db"; user = "scoring_user" },
    @{ db = "leaderboard_db"; user = "leaderboard_user" },
    @{ db = "notification_db"; user = "notification_user" }
)

foreach ($entry in $dbs) {
    $sql = "ALTER SCHEMA public OWNER TO $($entry.user);"
    Write-Host "Fixing $($entry.db) ..."
    docker exec aew-postgres psql -U postgres -d $entry.db -c $sql
    if ($LASTEXITCODE -ne 0) {
        Write-Error "Failed for $($entry.db)"
        exit 1
    }
}
Write-Host "Done. Restart app services: docker-compose restart auth-service user-service wrestler-service team-service league-service match-service scoring-service leaderboard-service notification-service"
