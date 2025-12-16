TRUNCATE TABLE
Website,
Category
RESTART IDENTITY CASCADE;

INSERT INTO Category (name) VALUES
('Phishing'),
('Malware'),
('Scam'),
('Fraud'),
('Bitcoin Scam');

INSERT INTO Website (domain, isSafe, confidence, reason, category, validated, lastValidated) VALUES
('fog.guacamoleboy.dk', FALSE, 95, 'Validated as unsafe | Safety: Phishing', 1, '2025-12-16 10:00:00', '2025-12-16 10:00:00'),
('ronau.dk', TRUE, NULL, 'Validated as safe', NULL, '2025-12-16 10:00:00', '2025-12-16 10:00:00');