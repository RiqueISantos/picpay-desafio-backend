CREATE TABLE wallets (
    id BIGSERIAL PRIMARY KEY,
    balance NUMERIC(15,2) DEFAULT 0 NOT NULL,
    user_id BIGINT UNIQUE NOT NULL,
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);
