INSERT INTO customer (id, name) VALUES (501, 'Amarpreet Bhatia');
INSERT INTO customer (id, name) VALUES (502, 'Jane Smith');

INSERT INTO phone_number (id, number, customer_id, active) VALUES (101, '061444444441', 501, true);
INSERT INTO phone_number (id, number, customer_id, active) VALUES (102, '061444444442', 502, false);