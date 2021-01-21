CREATE TABLE categoria(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
); --ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Jogos');
INSERT INTO categoria (nome) values ('Animes');
INSERT INTO categoria (nome) values ('Filmes');
INSERT INTO categoria (nome) values ('Seriados');
INSERT INTO categoria (nome) values ('Quadrinhos');
INSERT INTO categoria (nome) values ('Outros');
