package com.ever.cent.service;

import java.util.List;

import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;

public interface LancamentoService {
	
	public List<LancamentosResponseDTO> getLacamentos(Long userID);

	public List<LancamentosResponseDTO> getLancamentosPorMes(Long userId, Integer mes, Integer ano);

	public List<LancamentosResponseDTO> getLancamentosGastoPorMes(Long userId, Integer mes, Integer ano);

	public List<LancamentosResponseDTO> getLancamentosRendaPorMes(Long userId, Integer mes, Integer ano);

	
	public LancamentosResponseDTO getLancamentoByID(Long userID, Long lancamentoID);
	
	public LancamentosResponseDTO saveNovoLancamento(Long userID, LancamentoRequestDTO lancamento);
	
	public boolean deleteLancamentoByID(Long userID, Long lacamentoID);
	
	public LancamentosResponseDTO updateLancamento(Long userID, LancamentoRequestDTO lancamento);

}
