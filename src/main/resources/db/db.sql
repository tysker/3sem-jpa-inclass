-- Add 5 rows to the "truck" table
INSERT INTO truck (id, brand, capacity, is_available, registration_number)
VALUES
    (1, 'Volvo', 8000, true, 'A123-45-B6'),
    (2, 'Ford', 12000, false, 'C678-90-D1'),
    (3, 'Mercedes', 15000, true, 'E234-56-F7'),
    (4, 'BMW', 10000, false, 'G789-01-H2'),
    (5, 'Chevrolet', 17000, false, 'I345-67-J8');


-- Add 10 rows to the "driver" table
INSERT INTO driver (id, employment_date, name, surname, salary, truck_id)
VALUES
    ('230826-BD-398E', '2023-08-26', 'John', 'Doe', 12550, 1),
    ('200512-AD-786M', '1995-05-12', 'Alice', 'Smith', 18550, 2),
    ('211104-CE-572S', '1996-11-04', 'Charlie', 'Evans', 21800, 3),
    ('201217-GK-123D', '1997-12-17', 'Grace', 'Kelly', 52000, 4),
    ('220609-MP-456H', '1998-06-09', 'Michael', 'Parker', 31200, 5),
    ('200821-FR-892L', '1999-08-21', 'Frank', 'Roberts', 28500, 4),
    ('230531-WT-642B', '2023-05-31', 'William', 'Turner', 65800, 3),
    ('230304-LH-718R', '2023-03-04', 'Laura', 'Harris', 8500, 3),
    ('230925-SJ-599M', '2023-09-25', 'Sarah', 'Johnson', 16500, 5),
    ('211128-PT-347S', '2000-11-28', 'Peter', 'Taylor', 33000, 1);