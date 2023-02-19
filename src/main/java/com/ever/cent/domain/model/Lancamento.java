package com.ever.cent.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="lancamento", schema = "public")
public class Lancamento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lancamento_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
    private User user;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
    private TipoLancamento tipoLancamento;
	
	private String descricao;
	
	private BigDecimal valor;
	
	@Column(name= "data_lancamento")
	private Date dataLancamento;

}
