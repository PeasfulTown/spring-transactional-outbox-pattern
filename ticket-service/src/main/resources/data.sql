INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90'::uuid,
    '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d'::uuid,
    NULL,
    'Database Seeding Issue',
    'Encountered a syntax error while attempting to insert UUID data fields.',
    NOW(),
    NOW()
);

-- Record 2
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'::uuid,
    'f4e5d6c7-b8a9-0e1f-2a3b-4c5d6e7f8a90'::uuid,
    'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e'::uuid,
    'Payment Processing Failure',
    'Customer was charged twice during checkout for transaction ref #9948.',
    NOW() - INTERVAL '2 days',
    NOW() - INTERVAL '1 day'
);

-- Record 3
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e'::uuid,
    'f4e5d6c7-b8a9-0e1f-2a3b-4c5d6e7f8a90'::uuid,
    NULL,
    'Account Access Locked',
    'User entered the wrong password 5 times. Needs manual password profile unlock.',
    NOW() - INTERVAL '5 hours',
    NOW() - INTERVAL '5 hours'
);

-- Record 4
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f'::uuid,
    'e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f90a1'::uuid,
    'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e'::uuid,
    'Slow Page Load Times',
    'Dashboard takes over 12 seconds to load when fetching historical transaction logs.',
    NOW() - INTERVAL '1 day',
    NOW() - INTERVAL '2 hours'
);

-- Record 5
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f90'::uuid,
    'd4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f90'::uuid,
    NULL,
    'AMQP Outbox Queue Timeout',
    'Messages are stuck in the outbox_queue table and not hitting the main exchange.',
    NOW(),
    NOW()
);

-- Record 6
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f90a1'::uuid,
    'c3d4e5f6-a7b8-9c0d-1e2f-3a4b5c6d7e8f'::uuid,
    'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e'::uuid,
    'Incorrect Invoice Total',
    'Invoice #INV-2026-004 shows a 15% discount item but the grand total reflects full pricing.',
    NOW() - INTERVAL '4 days',
    NOW() - INTERVAL '3 days'
);

-- Record 7
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f90a1b2'::uuid,
    'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e'::uuid,
    NULL,
    'Mobile App Crash on Launch',
    'iOS application version 3.4.1 crashes immediately upon login on iPhone 15 devices.',
    NOW() - INTERVAL '10 hours',
    NOW() - INTERVAL '10 hours'
);

-- Record 8
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '7a8b9c0d-1e2f-3a4b-5c6d-7e8f90a1b2c3'::uuid,
    'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d'::uuid,
    '7a8b9c0d-1e2f-3a4b-5c6d-7e8f90a1b2c3'::uuid,
    'Request for GDPR Data Export',
    'Customer requested a full compressed archive download of personal identifiable history info.',
    NOW() - INTERVAL '6 days',
    NOW() - INTERVAL '5 days'
);

-- Record 9
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '8b9c0d1e-2f3a-4b5c-6d7e-8f90a1b2c3d4'::uuid,
    'e5f6a7b8-c9d0-1e2f-3a4b-5c6d7e8f90a1'::uuid,
    NULL,
    'Webhook Notification Dropping',
    'The order-service system is not receiving transactional events from the payment gateway endpoint.',
    NOW() - INTERVAL '3 hours',
    NOW() - INTERVAL '1 hour'
);

-- Record 10
INSERT INTO ticket (id, customer_id, assigned_agent_id, subject, description, created_at, updated_at)
VALUES (
    '9c0d1e2f-3a4b-5c6d-7e8f-90a1b2c3d4e5'::uuid,
    'c3d4e5f6-a7b8-9c0d-1e2f-3a4b5c6d7e8f'::uuid,
    '7a8b9c0d-1e2f-3a4b-5c6d-7e8f90a1b2c3'::uuid,
    'SLA Milestone Breach Warning',
    'High priority issue #4439 has remained in an unassigned status pool for longer than 48 hours.',
    NOW() - INTERVAL '2 days',
    NOW() - INTERVAL '2 days'
);
