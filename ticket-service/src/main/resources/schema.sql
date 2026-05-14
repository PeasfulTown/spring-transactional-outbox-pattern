DROP TABLE IF EXISTS ticket;

DROP TABLE IF EXISTS outbox;

DROP TYPE IF EXISTS outbox_status;

CREATE TABLE ticket (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    assigned_agent_id UUID,
    subject VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE TYPE outbox_status AS ENUM ('PENDING', 'PROCESSED', 'FAILED');

CREATE TABLE outbox (
    id UUID NOT NULL PRIMARY KEY,
    payload JSONB NOT NULL,
    status outbox_status NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_outbox_status_pending ON outbox (status) WHERE status = 'PENDING';