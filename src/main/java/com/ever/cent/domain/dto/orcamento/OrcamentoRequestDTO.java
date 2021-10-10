package com.ever.cent.domain.dto.orcamento;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoRequestDTO {
	
	public Integer tipo_id;
	public BigDecimal valor;

}
