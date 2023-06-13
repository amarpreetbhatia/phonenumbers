CREATE TABLE customer (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE phone_number (
    id BIGINT PRIMARY KEY,
    number VARCHAR(20),
    customer_id BIGINT,
    active BOOLEAN
);