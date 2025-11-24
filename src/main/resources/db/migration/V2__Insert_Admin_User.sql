-- Insert admin user with bcrypt hashed password for 'password'
-- The hash below is bcrypt hash of 'password' with cost factor 10
INSERT INTO "ADMINISTRADOR" ("USERNAME", "SENHA", "NOME", "CPF", "CARGO", "TIPO", "ATIVO", "DATA_CRIACAO")
VALUES (
    'admin',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'Administrator',
    '00000000000',
    'Admin',
    'ADMIN',
    true,
    CURRENT_TIMESTAMP
);
