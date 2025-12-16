DELETE FROM Website;
DELETE FROM Category;
DELETE FROM sqlite_sequence WHERE name='Website';
DELETE FROM sqlite_sequence WHERE name='Category';

INSERT INTO Category (name) VALUES 
('Phishing'), 
('Malware'), 
('Scam'), 
('Fraud'), 
('Bitcoin Scam');

INSERT INTO Website (domain, isSafe, confidence, reason, category, validated, lastValidated) VALUES
('fog.guacamoleboy.dk', 0, 95, 'Website reported by Guacamoleboy for Scam', 1, '2025-12-16T10:00:00Z', '2025-12-16T10:00:00Z'),
('https://ronau.dk', 1, null, 'Website reported by Guacamoleboy. Safe to be visited.', null, '2025-12-16T10:00:00Z', '2025-12-16T10:00:00Z');