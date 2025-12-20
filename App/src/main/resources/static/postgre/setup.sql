DROP TABLE IF EXISTS
Website,
Category,
scan_request,
ending_risk,
scan_result,
website_category,
reports,
website_reports,
website_report_categories
CASCADE;

CREATE TABLE category (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL UNIQUE
);

CREATE TABLE website (
id SERIAL PRIMARY KEY,
domain TEXT NOT NULL UNIQUE,                                            -- websites that have been scanned by us
is_safe BOOLEAN NOT NULL,                                               -- true or false
confidence INTEGER,                                                     -- null if it's a safe website
reason TEXT,
validated TIMESTAMP NOT NULL,
last_validated TIMESTAMP NOT NULL,
CHECK ((is_safe = TRUE AND confidence IS NULL)                          -- check since confidence uses Integer
OR(is_safe = FALSE AND confidence BETWEEN 1 AND 100))
);

CREATE TABLE website_category (                                         -- mange til mange relation
website_id INTEGER NOT NULL REFERENCES website(id) ON DELETE CASCADE,
category_id INTEGER NOT NULL REFERENCES category(id) ON DELETE CASCADE,
PRIMARY KEY (website_id, category_id)
);

CREATE TABLE scan_request (
id SERIAL PRIMARY KEY,
domain TEXT NOT NULL,                                                   -- websites that have been scanned by users
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
CHECK ((is_safe = TRUE AND confidence IS NULL)                          -- check since confidence uses Integer
OR (is_safe = FALSE AND confidence BETWEEN 1 AND 100))
);

CREATE TABLE ending_risk (
id SERIAL PRIMARY KEY,
ending TEXT NOT NULL UNIQUE,                                           -- dk, com, co.uk, gg
risk SMALLINT NOT NULL CHECK (risk BETWEEN 1 AND 5)                    -- 1 -> 5
);

CREATE TABLE reports (
id SERIAL PRIMARY KEY,
domain TEXT NOT NULL,                                                   -- Not unique
confidence INTEGER NOT NULL CHECK (confidence BETWEEN 1 AND 100),       -- How sure people are
category INTEGER REFERENCES category(id),                               -- Chosen category
reported_at TIMESTAMP NOT NULL DEFAULT now()                            -- Time of report
);

CREATE TABLE website_reports (
id SERIAL PRIMARY KEY,
domain TEXT NOT NULL UNIQUE,                                            -- Unique
confidence INTEGER NOT NULL CHECK (confidence BETWEEN 1 AND 100),       -- totalConfidence / totalReports.length
total_reports INTEGER NOT NULL,
last_reported TIMESTAMP NOT NULL,                                       -- Last public report
last_validated TIMESTAMP NOT NULL,                                      -- Last validation by / scanner
description TEXT NOT NULL                                               -- Display text (depends on confidence rating & category)
);

CREATE TABLE website_report_categories (
website_report_id INTEGER REFERENCES website_reports(id) ON DELETE CASCADE,
category_id INTEGER NOT NULL REFERENCES category(id),
report_count INTEGER NOT NULL,                                          -- Total Count
PRIMARY KEY (website_report_id, category_id)
);