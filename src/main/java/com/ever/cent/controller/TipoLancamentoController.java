package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.domain.model.TipoLancamento;
import com.ever.cent.repository.TipoLancamentoRepository;

@RestController
public class TipoLancamentoController {
	
	@Autowired
	public TipoLancamentoRepository repository;
	@GetMapping("/api/tipo-lancamento")
	public List<TipoLancamento> getLancamento() {
		return repository.findAll();
	}
}
