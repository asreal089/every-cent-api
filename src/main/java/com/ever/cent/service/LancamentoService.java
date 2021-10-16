package com.ever.cent.service;

import java.util.List;

import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;

public interface LancamentoService {
	
	public List<LancamentosResponseDTO> getLacamentos(Long userID);
	
	public LancamentosResponseDTO getLancamentoByID(Long orcamentoID);
	
	public LancamentosResponseDTO saveNovoLancamento(LancamentoRequestDTO orcamento);
	

}
