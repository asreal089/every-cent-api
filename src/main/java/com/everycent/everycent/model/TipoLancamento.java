package com.everycent.everycent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_lancamento")
public class TipoLancamento {
	
	@Id
	@Column(name = "tipo_id")
	private Long id;
	
	@Column(name = "tipo_nome")
	private String tipoNome;
	
	@Column(name = "tipo_descricao" )
	private String tipoDescricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoNome() {
		return tipoNome;
	}

	public void setTipoNome(String tipoNome) {
		this.tipoNome = tipoNome;
	}

	public String getTipoDescricao() {
		return tipoDescricao;
	}

	public void setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
	}
}
