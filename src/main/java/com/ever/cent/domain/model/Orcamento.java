package com.ever.cent.domain.model;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orcamento", schema = "public")
public class Orcamento {
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
	
	@Column(name="valor_limite")
	private DecimalFormat valor;
}
