CREATE TABLE tipo_lancamento(
	tipo_id serial PRIMARY KEY,
	tipo_nome varchar(100),
	is_renda boolean DEFAULT FALSE
);

INSERT INTO tipo_lancamento (tipo_nome, is_renda) VALUES ('salario', true);
INSERT INTO tipo_lancamento (tipo_nome, is_renda) VALUES ('proventos', true);
INSERT INTO tipo_lancamento (tipo_nome, is_renda) VALUES ('outras rendas', true);
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('comer fora');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('shopping');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('roupas');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('outros');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('seguro');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('cartão de credito');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('plano de saúde');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('educação');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('mercado');
INSERT INTO tipo_lancamento (tipo_nome) VALUES ('lazer');