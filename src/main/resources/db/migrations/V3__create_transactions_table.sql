CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(15,2) NOT NULL CHECK (amount > 0),
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_transaction_sender FOREIGN KEY (sender_id)
        REFERENCES users (id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_transaction_receiver FOREIGN KEY (receiver_id)
        REFERENCES users (id)
        ON DELETE RESTRICT
);
