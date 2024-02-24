-- Insert initial data into Assurance table
INSERT INTO assurance (assurance_number, created, updated)
VALUES ('Assurance1', '2021-03-14','2024-09-25'),
       ('Assurance2', '2021-03-14','2024-09-25');

-- Insert initial data into Auto table
INSERT INTO auto (assurance_id,model,registration_number, created, updated)
VALUES (1,'KIA','registration_number','2021-03-14','2024-09-25'),
       ( 1,'KIA','registration_number1','2021-03-14','2024-09-25'),
       ( 2,'KIA','registration_number12','2021-03-14','2024-09-25');
