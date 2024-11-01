CREATE TABLE IF NOT EXISTS products (
    id TEXT PRIMARY KEY,
    userId TEXT NOT NULL,
    price INTEGER NOT NULL,
    name TEXT NOT NULL,
    status TEXT NOT NULL
);