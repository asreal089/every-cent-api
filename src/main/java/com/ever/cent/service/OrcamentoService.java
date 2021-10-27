package com.ever.cent.service;

import java.util.List;

import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;

public interface OrcamentoService {
	
	public OrcamentoResponseDTO novoOrcamento(Long userID,OrcamentoRequestDTO orcamento);
	
	public List<OrcamentoResponseDTO> getOrcamentosByUserId(Long userID);
	
	public OrcamentoResponseDTO patch(Long userID, Long orcamentoID, OrcamentoRequestDTO orcamentoAtualizado);
	
	public Boolean delete(Long userID, Long orcamentoID);

}
