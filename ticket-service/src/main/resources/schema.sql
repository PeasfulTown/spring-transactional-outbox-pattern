DROP TABLE IF EXISTS ticket;
CREATE TABLE IF NOT EXISTS ticket (
    id UUID NOT NULL PRIMARY KEY,
    customer_id UUID NOT NULL,
    assigned_agent_id UUID,
    subject VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

