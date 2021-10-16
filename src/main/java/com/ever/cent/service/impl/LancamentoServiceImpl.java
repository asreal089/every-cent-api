package com.ever.cent.service.impl;

import java.util.List;

import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.service.LancamentoService;

public class LancamentoServiceImpl implements LancamentoService{

	@Override
	public List<LancamentosResponseDTO> getLacamentos(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LancamentosResponseDTO getLancamentoByID(Long userID, Long lancamentoID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LancamentosResponseDTO saveNovoLancamento(Long userID, LancamentoRequestDTO lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteLancamentoByID(Long userID, Long lacamentoID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LancamentosResponseDTO updateLancamento(Long userID, LancamentoRequestDTO lancamento) {
		// TODO Auto-generated method stub
		return null;
	}



}
