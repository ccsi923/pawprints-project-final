insert into users(dtype,username, password, user_email) values
('Admin','admin','$2a$10$P.EDNKPW25.IQyqFwVQ5.uwBfFEctsRuAFojgq1PFJh3k/3DPSMh2','admin@email.com'),
('Client','client','$2a$10$sFmVr1dS4yxWtdD5T8QP1eihHEaZGkjgPetZt37jeuNntsYxtkSlC','client@email.com');

insert into role (role, user_id) values
('ROLE_ADMIN', 1),
('ROLE_CLIENT', 1),
('ROLE_CLIENT', 2);