ALTER TABLE account_rates
    DROP COLUMN initial_balance,
    DROP COLUMN payment_frequency,
    DROP COLUMN payments_processed,
    DROP COLUMN next_payment_at,
    ADD COLUMN is_complex_rate boolean DEFAULT FALSE NOT NULL,
    ADD COLUMN cumulative_balance DECIMAL(15, 2) DEFAULT 0 NOT NULL;

ALTER TABLE accounts
    DROP COLUMN account_number;
