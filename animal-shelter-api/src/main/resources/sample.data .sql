INSERT INTO public.animal(id, created_date, last_modified_date, deleted, year_of_birth, type, gender, size, name, observation) VALUES

 (1, NOW(), NULL, false, 2020, 'D', 'M', 'S', 'Baiao Velho', NULL),
 (2, NOW(), NULL, false, 2021, 'D', 'M', 'S', 'Cabeca', NULL),
 (3, NOW(), NULL, false, 2022, 'D', 'M', 'M', 'Cris', NULL),
 (4, NOW(), NULL, false, 2020, 'D', 'M', 'M', 'Esquina', NULL),
 (5, NOW(), NULL, false, 2021, 'D', 'F', 'L', 'Ivani', NULL),
 (6, NOW(), NULL, false, 2022, 'D', 'M', 'L', 'Rex', NULL),
 (7, NOW(), NULL, false, 2020, 'C', 'F', 'L', 'Velha Gorda', NULL)
 
 ON CONFLICT DO NOTHING
 ;
