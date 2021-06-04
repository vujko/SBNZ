
INSERT INTO authority (name) VALUES
    ('ROLE_ADMIN');

INSERT INTO admin (id, email, password, first_name, last_name) VALUES
    (1, 'bodroza.joca@gmail.com', '$2y$10$dGD6UM2xBMUPdMVNWhQ.1e4FBJvKPWQSvfkU7lJywcc05U1GrlMwO', 'Jovan', 'Bodroza');

INSERT INTO user_authority (user_id, authority_id) VALUES
    (1,1);
	
INSERT INTO tag (type, name) VALUES
	('GENRE', 'fps'),
	('GENRE', 'tps'),
	('THEME', 'war'),
	('SPECIAL_SECTION', 'early access'),
	('PLATFORM', 'PC'),
	('PLAYER_SUPPORT', 'Multiplayer');
	
	
INSERT INTO game (name, developer, publisher, price, image, average_rating, downloads_num, raters_num, score) VALUES
	('Call Of Duty 2', 'Activision', 'Activision', 20.0, 'image1', 4.5, 1000, 0, 0),
	('Counter Strike 1.6', 'Valv Corporation', 'Valv Corporation', 15.0, 'image2', 4.0, 80, 0, 0),
	('PUBG', 'PUBG Corporation', 'PUBG Corporation', 30.0, 'image3', 0.0, 0, 0, 0),
	('PUBG New State', 'PUBG Corporation', 'PUBG Corporation', 100.0, 'image4', 0.0, 0, 0, 0);
	
	
INSERT INTO game_tags (game_id, tags_id) VALUES
	(1, 1),
	(1, 3),
	(1, 5),
	(2, 1),
	(2, 5),
	(3, 2),
	(3, 6),
	(4, 4);
		
	