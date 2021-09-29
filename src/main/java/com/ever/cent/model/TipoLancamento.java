package com.ever.cent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@Table(name="tipo_lancamento")
@AllArgsConstructor
public class TipoLancamento {
	@Id
	@Column(name="tipo_id")
	public Integer id;
	@Column(name="tipo_nome")
	public String tipo;
	@Column(name="is_renda")
	public Boolean isRenda;
	
}
