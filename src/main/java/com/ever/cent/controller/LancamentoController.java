package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.service.impl.LancamentoServiceImpl;

@RestController
@RequestMapping("api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoServiceImpl service;
	
	@GetMapping("/{userID}")
	public List<LancamentosResponseDTO> getLancamentoByUserId(@PathVariable(value = "userID") String userID) {
		return service.getLacamentos(Long.valueOf(userID));
	}

	@GetMapping("/{userID}/{lancamentoID}")
	public LancamentosResponseDTO getLancamentoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "lancamentoID") String lancamentoID) {
		return service.getLancamentoByID(Long.valueOf(userID), Long.valueOf(lancamentoID));
	}

	@PostMapping("/{userID}")
	public LancamentosResponseDTO postLancamento(@PathVariable(value = "userID") String userID,
			@RequestBody LancamentoRequestDTO lancamento) {
		return service.saveNovoLancamento(Long.valueOf(userID), lancamento);
	}
	
	@DeleteMapping("/{userID}/{lancamentoID}")
	public boolean deleteLancamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "lancamentoID") String lancamentoID) {
		boolean response = service.deleteLancamentoByID(Long.valueOf(userID), Long.valueOf(lancamentoID));
		return response;
	}
	
	@PatchMapping("/{userID}/{lancamentoID}")
	public LancamentosResponseDTO patchLancamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "lancamentoID") String lancamentoID, @RequestBody LancamentoRequestDTO lancamento ) {
		LancamentosResponseDTO response = service.updateLancamento(Long.valueOf(userID), lancamento);
		return response;
	}
}
