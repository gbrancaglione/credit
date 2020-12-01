CREATE SEQUENCE public.hibernate_sequence;

CREATE TABLE IF NOT EXISTS request (
    id BIGINT PRIMARY KEY NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(255),
    telephone_number VARCHAR(15),
    value DOUBLE PRECISION,
    number_of_installments INTEGER,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
