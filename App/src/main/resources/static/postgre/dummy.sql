TRUNCATE TABLE
Website,
Category,
scan_request,
ending_risk,
scan_result,
website_category
RESTART IDENTITY CASCADE;

INSERT INTO Category (name) VALUES
('Phishing'),
('Malware'),
('Scam'),
('Fraud'),
('Bitcoin Scam');

INSERT INTO Website (domain, is_safe, confidence, reason, validated, last_validated) VALUES
('fog.guacamoleboy.dk', FALSE, 95, 'Validated as unsafe | Safety: Phishing',  '2025-12-16 10:00:00', '2025-12-16 10:00:00'),
('https://fog.guacamoleboy.dk', FALSE, 95, 'Validated as unsafe | Safety: Phishing',  '2025-12-16 10:00:00', '2025-12-16 10:00:00'),
('https://fog.guacamoleboy.dk/', FALSE, 95, 'Validated as unsafe | Safety: Phishing',  '2025-12-16 10:00:00', '2025-12-16 10:00:00'),
('https://www.fog.guacamoleboy.dk', FALSE, 95, 'Validated as unsafe | Safety: Phishing',  '2025-12-16 10:00:00', '2025-12-16 10:00:00'),
('ronau.dk', TRUE, NULL, 'Validated as safe', '2025-12-16 10:00:00', '2025-12-16 10:00:00');

INSERT INTO website_category (website_id, category_id) VALUES
(1, 1),
(1, 3);

INSERT INTO scan_request (domain, status, source) VALUES
('Website.gg', 'OPEN', 'firefox'),
('website.xyz', 'OPEN', 'chrome'),
('website.com', 'OPEN', 'Brave'),
('ronau.dk', 'CLOSED', 'Opera');

INSERT INTO ending_risk (ending, risk) VALUES
('dk', 1),
('co.uk', 1),
('com', 1),
('net', 1),
('org', 2),
('gg', 3),
('xyz', 4),
('top', 5);

INSERT INTO scan_result (scan_request_id, domain, is_safe, confidence, reason, category, scanned_at) VALUES
(1, 'website.gg', FALSE, 80, 'Suspicious TLD + phishing patterns', 1, '2025-12-16 10:30:00'),
(2, 'website.xyz', FALSE, 92, 'Known scam pattern + TLS missing', 2, '2025-12-16 10:45:00'),
(3, 'ronau.dk', TRUE, NULL, 'Safe domain validated', NULL, '2025-12-16 11:00:00');