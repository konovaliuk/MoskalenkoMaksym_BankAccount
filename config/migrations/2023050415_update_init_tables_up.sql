ALTER TABLE account_rates
    ADD COLUMN initial_balance DECIMAL DEFAULT 0,
    ADD COLUMN payment_frequency INTEGER,
    ADD COLUMN payments_processed INTEGER DEFAULT 0,
    ADD COLUMN next_payment_at timestamp,
    DROP COLUMN is_complex_rate,
    DROP COLUMN cumulative_balance;

ALTER TABLE accounts
    ADD COLUMN account_number VARCHAR UNIQUE NOT NULL;
