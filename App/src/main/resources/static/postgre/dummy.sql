TRUNCATE TABLE
Website,
Category,
scan_request,
ending_risk,
scan_result,
website_category,
reports,
website_reports,
website_report_categories
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

INSERT INTO reports (domain, confidence, category, reported_at) VALUES
('fog.guacamoleboy.dk', 90, 1, '2025-12-17 09:00:00'),
('fog.guacamoleboy.dk', 95, 1, '2025-12-17 09:10:00'),
('fog.guacamoleboy.dk', 85, 3, '2025-12-17 09:20:00'),
('fog.guacamoleboy.dk', 88, 3, '2025-12-17 09:30:00'),
('fog.guacamoleboy.dk', 92, 4, '2025-12-17 09:40:00'),
('website.gg', 75, 3, '2025-12-17 10:00:00'),
('website.gg', 82, 3, '2025-12-17 10:05:00'),
('website.gg', 70, 5, '2025-12-17 10:10:00'),
('website.xyz', 95, 2, '2025-12-17 11:00:00'),
('website.xyz', 90, 2, '2025-12-17 11:05:00');

INSERT INTO website_reports (domain,confidence,total_reports,last_reported,last_validated,description) VALUES
(
  'fog.guacamoleboy.dk',
  90,
  5,
  '2025-12-17 09:40:00',
  '2025-12-17 10:00:00',
  'This website is frequently reported for phishing and scam-related activity.'
),
(
  'website.gg',
  76,
  3,
  '2025-12-17 10:10:00',
  '2025-12-17 10:30:00',
  'Users report this website as a potential scam.'
),
(
  'website.xyz',
  92,
  2,
  '2025-12-17 11:05:00',
  '2025-12-17 11:15:00',
  'High confidence malware reports detected.'
);

INSERT INTO website_report_categories (website_report_id,category_id, report_count) VALUES
(1, 1, 2),
(1, 3, 2),
(1, 4, 1),
(2, 3, 2),
(2, 5, 1),
(3, 2, 2);