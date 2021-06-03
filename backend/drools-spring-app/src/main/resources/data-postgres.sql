
INSERT INTO authority (name) VALUES
    ('ROLE_ADMIN');

INSERT INTO admin (id, email, password, first_name, last_name) VALUES
    (1, 'bodroza.joca@gmail.com', '$2y$10$dGD6UM2xBMUPdMVNWhQ.1e4FBJvKPWQSvfkU7lJywcc05U1GrlMwO', 'Jovan', 'Bodroza');

INSERT INTO user_authority (user_id, authority_id) VALUES
    (1,1);
	
INSERT INTO tag (type, name) VALUES
	('GENRE', 'fps'),
	('GENRE', 'tps');
	
	
INSERT INTO game (name, developer, publisher, price, image, average_rating, downloads_num, raters_num, score) VALUES
	('Call Of Duty 2', 'Activision', 'Activision', 20.0, 'image1', 0.0, 0, 0, 0),
	('Counter Strike 1.6', 'Valv Corporation', 'Valv Corporation', 15.0, 'image2', 0.0, 0, 0, 0),
	('PUBG', 'PUBG Corporation', 'PUBG Corporation', 30.0, 'image3', 0.0, 0, 0, 0);
	
INSERT INTO game_tags (game_id, tags_id) VALUES
	(1, 1),
	(2, 1),
	(3, 2);
		
	