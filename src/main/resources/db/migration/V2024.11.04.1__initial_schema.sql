CREATE TABLE payment_instruction
(
    id                       BIGSERIAL PRIMARY KEY,
    payer_full_name          VARCHAR(255)   NOT NULL,
    payer_inn                VARCHAR(10)    NOT NULL,
    payer_card_number        VARCHAR(20)    NOT NULL,
    recipient_account_number VARCHAR(34)    NOT NULL,
    recipient_mfo            VARCHAR(6)     NOT NULL,
    recipient_okpo           VARCHAR(8)     NOT NULL,
    recipient_name           VARCHAR(255)   NOT NULL,
    period_interval INTERVAL NOT NULL,
    payment_amount           DECIMAL(18, 2) NOT NULL
);

COMMENT
ON TABLE payment_instruction IS 'Stores recurring payment instructions';
COMMENT
ON COLUMN payment_instruction.payer_full_name IS 'Full name of the payer';
COMMENT
ON COLUMN payment_instruction.payer_inn IS 'Taxpayer Identification Number of the payer';
COMMENT
ON COLUMN payment_instruction.payer_card_number IS 'Payer’s card number for the debit transaction';
COMMENT
ON COLUMN payment_instruction.recipient_account_number IS 'Recipient’s bank account number';
COMMENT
ON COLUMN payment_instruction.recipient_mfo IS 'Recipient’s MFO code (bank identifier)';
COMMENT
ON COLUMN payment_instruction.recipient_okpo IS 'Recipient’s OKPO code';
COMMENT
ON COLUMN payment_instruction.recipient_name IS 'Recipient’s name or business name';
COMMENT
ON COLUMN payment_instruction.period_interval IS 'Interval for payment recurrence (e.g., every N minutes, hours, or days)';
COMMENT
ON COLUMN payment_instruction.payment_amount IS 'Amount to be debited in each transaction';

CREATE TABLE "transaction"
(
    id                     BIGSERIAL PRIMARY KEY,
    payment_instruction_id BIGSERIAL,
    transaction_date       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    transaction_amount     DECIMAL(18, 2) NOT NULL,
    status                 CHAR(1)        NOT NULL,
    FOREIGN KEY (payment_instruction_id) REFERENCES payment_instruction (id)
);

COMMENT
ON TABLE "transaction" IS 'Stores each transaction result of a recurring payment';
COMMENT
ON COLUMN "transaction".payment_instruction_id IS 'Foreign key linking to the payment instruction';
COMMENT
ON COLUMN "transaction".transaction_date IS 'Timestamp when the transaction was executed';
COMMENT
ON COLUMN "transaction".transaction_amount IS 'Amount processed in the transaction';
COMMENT
ON COLUMN "transaction".status IS 'Transaction status (A=Active, S=Storned)';
