

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create TABLE tournaments (
	tournament_id SERIAL NOT NULL,
	participants int NOT NULL,
	winner int,
	admin_user int NOT NULL,
	tournament_status int NOT NULL,
	tournament_type int NOT NULL,
	CONSTRAINT PK_tournament PRIMARY KEY (tournament_id)


);

create TABLE players (
	player_id SERIAL NOT NULL,
	wins int NOT NULL,
	losses int NOT NULL,
	user_id int NOT NULL,

	CONSTRAINT PK_player PRIMARY KEY (player_id)

);

create TABLE tournament_users (
	tournament_id int NOT NULL,
	player_id int NOT NULL

);

create TABLE tournament_type (
	tournament_type_id int NOT NULL,
	tournament_type_name varchar(20),

	CONSTRAINT PK_tournament_type_id PRIMARY KEY (tournament_type_id)
);

	ALTER TABLE tournaments ADD CONSTRAINT FK_winner FOREIGN KEY (winner) REFERENCES players(player_id);
	ALTER TABLE tournaments ADD CONSTRAINT FK_admin_user FOREIGN KEY (admin_user) REFERENCES players(player_id);
	ALTER TABLE tournaments ADD CONSTRAINT FK_tournament_type FOREIGN KEY (tournament_type) REFERENCES tournament_type(tournament_type_id);
	ALTER TABLE players ADD CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id);
	ALTER TABLE tournament_users ADD CONSTRAINT FK_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id);
	ALTER TABLE tournament_users ADD CONSTRAINT FK_player_id FOREIGN KEY (player_id) REFERENCES players(player_id);


