ALTER TABLE offer
    ALTER COLUMN total_value SET DATA TYPE NUMERIC(9, 2),
    ALTER COLUMN installment_value SET DATA TYPE NUMERIC(9, 2);

ALTER TABLE request
    ALTER COLUMN value SET DATA TYPE NUMERIC(9, 2);