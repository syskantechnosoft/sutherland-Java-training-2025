-- Sample users for testing (passwords are 'password123')
-- Admin user
INSERT INTO user_info (name, email, password, roles) VALUES 
('Admin User', 'admin@ars.com', '$2a$12$LQv3c1yqBaTVfC9gfUPy/.1A/2IdlQkrUe/YjEUmirRSwEn/4e70O', 'ROLE_ADMIN');

-- Regular user
INSERT INTO user_info (name, email, password, roles) VALUES 
('John Doe', 'user@ars.com', '$2a$12$LQv3c1yqBaTVfC9gfUPy/.1A/2IdlQkrUe/YjEUmirRSwEn/4e70O', 'ROLE_USER');

-- User with multiple roles
INSERT INTO user_info (name, email, password, roles) VALUES 
('Jane Smith', 'jane@ars.com', '$2a$12$LQv3c1yqBaTVfC9gfUPy/.1A/2IdlQkrUe/YjEUmirRSwEn/4e70O', 'ROLE_USER,ROLE_ADMIN');