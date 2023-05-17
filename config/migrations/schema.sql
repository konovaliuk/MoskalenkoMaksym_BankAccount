CREATE TABLE profiles (
    id serial PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    password_hash varchar NOT NULL,
    role VARCHAR DEFAULT 'user' NOT NULL,
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW()
);

CREATE TABLE accounts (
    id serial PRIMARY KEY,
    account_number VARCHAR UNIQUE NOT NULL,
    profile_id INTEGER REFERENCES profiles (id) NOT NULL,
    type VARCHAR DEFAULT 'default' NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0 NOT NULL,
    status VARCHAR DEFAULT 'open' NOT NULL,
    expired_at timestamp,
    opened_at timestamp,
    closed_at timestamp,
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW()
);

CREATE TABLE account_rates (
    account_id INTEGER REFERENCES accounts (id) PRIMARY KEY,
    rate DECIMAL(5, 2) DEFAULT 0 NOT NULL,
    initial_balance DECIMAL DEFAULT 0,
    payment_frequency INTEGER,
    payments_processed INTEGER DEFAULT 0,
    next_payment_at timestamp
);

CREATE TABLE credit_approves (
    account_id INTEGER REFERENCES accounts (id) PRIMARY KEY,
    approver_id INTEGER REFERENCES profiles (id) NOT NULL
);

CREATE TABLE transactions (
    id serial PRIMARY KEY,
    src_account INTEGER REFERENCES accounts (id),
    dst_account INTEGER REFERENCES accounts (id),
    volume DECIMAL(15,2) NOT NULL,
    type VARCHAR NOT NULL,
    executed_at timestamp DEFAULT NOW(),
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW()
);

