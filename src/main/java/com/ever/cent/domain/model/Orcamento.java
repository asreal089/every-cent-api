package com.ever.cent.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="orcamento", schema = "public")
public class Orcamento {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orcamento_id")
	public Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
    public User user;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
    public TipoLancamento tipoLancamento;
	
	@Column(name="valor_limite")
	public BigDecimal valor;
}
