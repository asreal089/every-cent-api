package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;
import com.ever.cent.domain.model.Orcamento;
import com.ever.cent.repository.OrcamentoRepository;
import com.ever.cent.service.impl.OrcamentoServiceImpl;

@RestController
@RequestMapping("api/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoRepository orcamentoRepo;

	@Autowired
	private OrcamentoServiceImpl orcamentoService;
	
	@GetMapping("/{userID}")
	public List<Orcamento> getOrcamentoByUserId(@PathVariable(value = "userID") String userID) {
		return orcamentoRepo.findByUserId(Long.valueOf(userID));
	}

	@PostMapping("/{userID}")
	public OrcamentoResponseDTO postOrcamento(@PathVariable(value = "userID") String userID, @RequestBody OrcamentoRequestDTO orcamento) {
		OrcamentoResponseDTO response = orcamentoService.novoOrcamento(Long.valueOf(userID), orcamento);
		return response;
	}


}
