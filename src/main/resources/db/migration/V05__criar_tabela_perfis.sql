CREATE TABLE Perfis (
	user_id SERIAL,
	PERFIS VARCHAR(50) NOT NULL,
	CONSTRAINT fk_perfil FOREIGN KEY (user_id) REFERENCES Users(id)
);

INSERT INTO Perfis (PERFIS, user_id) values ('ADMIN', 1);
