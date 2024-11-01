# commands
mvn flyway:migrate
mvn -Dflyway.cleanDisabled=false flyway:clean

# init table for sqlite
CREATE TABLE IF NOT EXISTS flyway_schema_history (
    installed_rank INTEGER NOT NULL PRIMARY KEY,
    version TEXT,
    description TEXT NOT NULL,
    type TEXT NOT NULL,
    script TEXT NOT NULL,
    checksum INTEGER,
    installed_by TEXT NOT NULL,
    installed_on TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    execution_time INTEGER NOT NULL,
    success INTEGER NOT NULL -- BOOLEANの代わりにINTEGERを使用
);
