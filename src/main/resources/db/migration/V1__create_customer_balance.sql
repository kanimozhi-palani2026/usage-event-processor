CREATE TABLE customer_balance (
                                  customer_id BIGINT      NOT NULL PRIMARY KEY,
                                  balance     BIGINT      NOT NULL,          -- in MB
                                  updated_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Seed data matching the example in the spec
INSERT INTO customer_balance (customer_id, balance) VALUES (101, 500);