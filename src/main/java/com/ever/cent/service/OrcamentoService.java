package com.ever.cent.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;

public interface OrcamentoService {
	
	public ResponseEntity<String> novoOrcamento(Long userID,OrcamentoRequestDTO orcamento);
	
	public List<OrcamentoResponseDTO> getOrcamentosByUserId(Long userID);
	

}
