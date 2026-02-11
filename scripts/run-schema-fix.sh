#!/bin/sh
# Run ALTER SCHEMA public OWNER TO for each DB (fixes "permission denied for schema public").
# Used by postgres-schema-fix service. Idempotent - safe to run every time.
HOST="${PGHOST:-postgres}"
USER="${PGUSER:-postgres}"
PASS="${PGPASSWORD:-amine123}"

# Use .pgpass so psql always gets the password (env can be lost when script is piped to sh)
mkdir -p /tmp/.pgpass.d
PGPASSFILE=/tmp/.pgpass.d/pgpass
echo "$HOST:5432:*:$USER:$PASS" > "$PGPASSFILE"
chmod 600 "$PGPASSFILE"
export PGPASSFILE
export PGPASSWORD="$PASS"

# Wait for postgres to accept connections (try current password, then 'postgres' for old volumes)
echo "Waiting for Postgres at $HOST..."
try_connect() {
  psql -h "$HOST" -U "$USER" -d postgres -c "SELECT 1" >/dev/null 2>&1
}
for i in 1 2 3 4 5 6 7 8 9 10; do
  if try_connect; then
    break
  fi
  if [ "$i" = "5" ] && [ "$PASS" = "amine123" ]; then
    echo "Trying fallback password 'postgres' for old volumes..."
    PASS=postgres
    export PGPASSWORD=postgres
    echo "$HOST:5432:*:$USER:$PASS" > "$PGPASSFILE"
  fi
  if [ "$i" = "10" ]; then
    echo "ERROR: Could not connect to Postgres. Set PGPASSWORD to match your postgres user."
    exit 2
  fi
  sleep 2
done
echo "Connected."

ok=0
for pair in "auth_db:auth_user" "user_db:user_user" "wrestler_db:wrestler_user" "team_db:team_user" "league_db:league_user" "match_db:match_user" "scoring_db:scoring_user" "leaderboard_db:leaderboard_user" "notification_db:notification_user"; do
  db="${pair%%:*}"
  usr="${pair##*:}"
  echo "Setting schema public owner for $db to $usr ..."
  if psql -h "$HOST" -U "$USER" -d "$db" -c "ALTER SCHEMA public OWNER TO $usr;"; then
    ok=1
  else
    echo "  (skipped $db - may not exist yet)"
  fi
done
echo "Schema fix done."
exit 0
