CREATE TABLE tipo_lancamento(
	tipo_id serial PRIMARY KEY,
	tipo_nome varchar(100),
	is_renda boolean DEFAULT FALSE
);

INSERT INTO tipo_lancamento (tipo_nome, is_renda) VALUES ('Salário', true);
INSERT INTO tipo_lancamento (tipo_nome, is_renda) VALUES ('Proventos', true);
INSERT INTO tipo_lancamento (tipo_nome, is_renda) VALUES ('Outras rendas', true);
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Moradia');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Transporte');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Alimentação');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Utilidades');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Saúde');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Educação');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Roupas');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Lazer Entreterimento');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Seguro');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Investimentos');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('Outros');