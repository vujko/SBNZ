
INSERT INTO authority (name) VALUES
    ('ROLE_ADMIN'),
	('ROLE_USER');

INSERT INTO admin (id, email, password, first_name, last_name) VALUES
    (101, 'email@email.com', '$2a$10$9Q8.wc6RCsiAPmNdnxYwwue9aKiVGWlT4U/oKrs3oFpj05Cv2Qt0q', 'Jovan', 'Bodroza');
	
INSERT INTO registered_user (id, email, password, first_name, last_name) VALUES
	(102, 'bodroza.joca1@gmail.com', '$2a$10$9Q8.wc6RCsiAPmNdnxYwwue9aKiVGWlT4U/oKrs3oFpj05Cv2Qt0q', 'Vukasin', 'Jokic'),
	(103, 'bodroza.joca2@gmail.com', '$2a$10$9Q8.wc6RCsiAPmNdnxYwwue9aKiVGWlT4U/oKrs3oFpj05Cv2Qt0q', 'Jovan', 'Jovanovic'),
	(104, 'bodroza.joca3@gmail.com', '$2a$10$9Q8.wc6RCsiAPmNdnxYwwue9aKiVGWlT4U/oKrs3oFpj05Cv2Qt0q', 'Nikola', 'Nikolic');

INSERT INTO user_authority (user_id, authority_id) VALUES
    (101,1),
	(102,2),
	(103,2),
	(104,2);
	
INSERT INTO tag (type, name) VALUES
	('GENRE', 'fps'),
	('GENRE', 'tps'),
	('THEME', 'war'),
	('SPECIAL_SECTION', 'early access'),
	('PLATFORM', 'PC'),
	('PLAYER_SUPPORT', 'Multiplayer');
	
	
INSERT INTO game (name, developer, publisher, price, image, average_rating, downloads_num, raters_num, score) VALUES
	('Call Of Duty 2', 'Activision', 'Activision', 20.0, 'image1', 4.5, 10000, 1200, 0),
	('Counter Strike 1.6', 'Valv Corporation', 'Valv Corporation', 0.0, 'image2', 4.0, 80, 0, 0),
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
	
INSERT INTO registered_user_tags VALUES
	(102,1),
	(102,3),
	(102,6),
	(103,1),
	(103,5),
	(104,2),
	(104,6);
		
	