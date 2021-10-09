CREATE TABLE lancamento(
	lancamento_id BIGSERIAL PRIMARY KEY,
	user_id bigint,
	tipo_id int,
	descricao varchar(50),
	data_lancamento date,
	valor numeric(7,2),
	FOREIGN KEY (user_id) REFERENCES "user"(user_id),
	FOREIGN KEY (tipo_id) REFERENCES tipo_lancamento(tipo_id)
);

create table orcamento(
	user_id bigint,
	tipo_id int,
	valor_limite numeric(7,2),
	FOREIGN KEY (user_id) REFERENCES "user"(user_id),
	FOREIGN KEY (tipo_id) REFERENCES tipo_lancamento(tipo_id),
	PRIMARY KEY (user_id, tipo_id)	
);
