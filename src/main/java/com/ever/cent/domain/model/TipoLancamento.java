package com.ever.cent.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="tipo_lancamento")
public class TipoLancamento {
	@Id
	@Column(name="tipo_id")
	public Integer id;
	@Column(name="tipo_nome")
	public String tipo;
	@Column(name="is_renda")
	public Boolean isRenda;
	
}
