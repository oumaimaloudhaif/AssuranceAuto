INSERT INTO assurance (assurance_number, created, updated)
VALUES ('Assurance1', NOW(), NOW()),
       ('Assurance2', NOW(), NOW());

-- Insert initial data into Auto table
INSERT INTO auto (assurance_id,model, created, updated)
VALUES (1,'KIA',NOW(), NOW()),
       ( 1,'KIA', NOW(), NOW()),
       ( 2,'KIA', NOW(), NOW());