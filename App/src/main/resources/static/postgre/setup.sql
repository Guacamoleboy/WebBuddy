DROP TABLE IF EXISTS
Website,
Category,
scan_request,
ending_risk,
scan_result
CASCADE;

CREATE TABLE category (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL UNIQUE
);

CREATE TABLE website (
id SERIAL PRIMARY KEY,
domain TEXT NOT NULL UNIQUE,
is_safe BOOLEAN NOT NULL,
confidence INTEGER,                                                     -- null if it's a safe website
reason TEXT,
category INTEGER REFERENCES category(id),
validated TIMESTAMP NOT NULL,
last_validated TIMESTAMP NOT NULL,
CHECK (
    (is_safe = TRUE AND confidence IS NULL)
    OR
    (is_safe = FALSE AND confidence BETWEEN 1 AND 100)
)
);

CREATE TABLE scan_request (
id SERIAL PRIMARY KEY,
domain TEXT NOT NULL,
requested_at TIMESTAMP NOT NULL DEFAULT now(),
status TEXT NOT NULL,                                                   -- Open,Closed
source TEXT,                                                            -- Browser (firefox, chrome, opera)
UNIQUE (domain, status)
);

CREATE TABLE scan_result (
id SERIAL PRIMARY KEY,
scan_request_id INTEGER REFERENCES scan_request(id) ON DELETE CASCADE,
domain TEXT NOT NULL,
is_safe BOOLEAN NOT NULL,
confidence INTEGER,
reason TEXT,
category INTEGER REFERENCES category(id),
scanned_at TIMESTAMP NOT NULL DEFAULT now(),
CHECK (
    (is_safe = TRUE AND confidence IS NULL)
    OR
    (is_safe = FALSE AND confidence BETWEEN 1 AND 100)
)
);

CREATE TABLE ending_risk (
id SERIAL PRIMARY KEY,
ending TEXT NOT NULL UNIQUE,                                           -- dk, com, co.uk, gg
risk SMALLINT NOT NULL CHECK (risk BETWEEN 1 AND 5)                    -- 1 -> 5
);