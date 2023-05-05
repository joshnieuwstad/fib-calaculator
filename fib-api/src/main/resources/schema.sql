DROP TABLE IF EXISTS seen_indices CASCADE;

CREATE TABLE IF NOT EXISTS seen_indices (
    id serial PRIMARY KEY NOT NULL,
    fib_index varchar(50) NOT NULL
);
