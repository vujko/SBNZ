
INSERT INTO authority (name) VALUES
    ('ROLE_ADMIN');

INSERT INTO admin (id, email, password, first_name, last_name) VALUES
    (1, 'bodroza.joca@gmail.com', '$2y$10$dGD6UM2xBMUPdMVNWhQ.1e4FBJvKPWQSvfkU7lJywcc05U1GrlMwO', 'Jovan', 'Bodroza');

INSERT INTO user_authority (user_id, authority_id) VALUES
    (1,1);
	
INSERT INTO tag (type, name) VALUES
	('GENRE', 'war');
		
	