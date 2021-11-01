package com.ever.cent.domain.dto.lacamento;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentosResponseDTO {
	public Long userID;
	public Long lancamentoID;
	public Integer tipoID;
	public String tipo;
	public boolean isRenda;
	public String descricao;
	public BigDecimal valor;
	public Date data_lacamento;
}
