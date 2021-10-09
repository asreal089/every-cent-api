package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.domain.model.Orcamento;
import com.ever.cent.repository.OrcamentoRepository;

@RestController
@RequestMapping("api/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoRepository repo;
	
	@GetMapping("/{userID}")
	public List<Orcamento> getOrcamentoByUserId(@PathVariable(value="userID") Long userID) {
		return repo.findByUser(userID); 
	}
	
	@PostMapping
	public Orcamento postOrcamento(Orcamento orcamento) {
		Orcamento savedEntity = repo.save(orcamento);
		return savedEntity;
	}
}
