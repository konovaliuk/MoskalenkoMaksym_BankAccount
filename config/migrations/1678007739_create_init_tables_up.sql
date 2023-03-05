CREATE TYPE profile_role AS ENUM (
    'user',
    'admin',
    'superadmin'
);

CREATE TYPE account_type AS ENUM (
    'credit',
    'debit',
    'default'
);

CREATE TYPE account_status AS ENUM (
    'processing',
    'open',
    'closed'
);

CREATE TYPE transaction_type AS ENUM (
    'transfer',
    'deposit',
    'withdraw'
);

CREATE TABLE profiles (
    id uuid DEFAULT gen_random_uuid () PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    password_hash varchar NOT NULL,
    role profile_role DEFAULT 'user' NOT NULL,
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW()
);

CREATE TABLE accounts (
    id uuid DEFAULT gen_random_uuid () PRIMARY KEY,
    profile_id uuid REFERENCES profiles (id) NOT NULL,
    account_type account_type DEFAULT 'default' NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0 NOT NULL,
    status account_status DEFAULT 'open' NOT NULL,
    expired_at timestamp,
    opened_at timestamp,
    closed_at timestamp,
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW()
);

CREATE TABLE account_rates (
    account_id uuid REFERENCES accounts (id) PRIMARY KEY,
    cumulative_balance DECIMAL(15, 2) DEFAULT 0 NOT NULL,
    rate DECIMAL(5, 2) DEFAULT 0 NOT NULL,
    is_complex_rate boolean DEFAULT FALSE NOT NULL
);

CREATE TABLE credit_approves (
    account_id uuid REFERENCES accounts (id) PRIMARY KEY,
    approver_id uuid REFERENCES profiles (id) NOT NULL
);

CREATE TABLE transactions (
    id uuid DEFAULT gen_random_uuid () PRIMARY KEY,
    src_account uuid REFERENCES accounts (id) ,
    dst_account uuid REFERENCES accounts (id),
    volume DECIMAL(15,2) NOT NULL,
    transaction_type transaction_type NOT NULL,
    executed_at timestamp DEFAULT NOW(),
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW()
);

