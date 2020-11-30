CREATE TABLE IF NOT EXISTS hibernate_sequence (
    next_val BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS requests (
    id BIGINT PRIMARY KEY NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(255),
    telephone_number VARCHAR(15),
    value DOUBLE,
    number_of_installments INTEGER,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO hibernate_sequence VALUES (1);