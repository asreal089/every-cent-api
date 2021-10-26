package com.ever.cent.service;

import java.util.List;

import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.exception.ForbidenAccessException;

public interface LancamentoService {
	
	public List<LancamentosResponseDTO> getLacamentos(Long userID);
	
	public LancamentosResponseDTO getLancamentoByID(Long userID, Long lancamentoID);
	
	public LancamentosResponseDTO saveNovoLancamento(Long userID, LancamentoRequestDTO lancamento);
	
	public boolean deleteLancamentoByID(Long userID, Long lacamentoID);
	
	public LancamentosResponseDTO updateLancamento(Long userID, LancamentoRequestDTO lancamento);

}
