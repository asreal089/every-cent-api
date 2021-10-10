package com.ever.cent.domain.dto.orcamento;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrcamentoResponseDTO {
	public Long id;
	public int tipo_id;
	public String tipo;
	public boolean is_renda;
	public BigDecimal valor;
}
