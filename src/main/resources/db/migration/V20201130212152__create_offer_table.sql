CREATE TABLE IF NOT EXISTS offer (
       id BIGINT PRIMARY KEY NOT NULL,
       request_id BIGINT NOT NULL,
       total_value DOUBLE PRECISION,
       number_of_installments INTEGER,
       installment_value DOUBLE PRECISION,

       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP,

       FOREIGN KEY (request_id) REFERENCES request(id)
);
