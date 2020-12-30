package com.everycent.everycent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everycent.everycent.repository.TipoLancamentoRepository;

@RestController
@RequestMapping("tipo-lancamento")
public class TipoLancamentoController {
	
	@Autowired
	TipoLancamentoRepository tipoLancamentoRepository;
	
	@GetMapping
	public ResponseEntity<?> getTiposLancamento(){
		List <?> tiposLancamentos = tipoLancamentoRepository.findAll();
		return ResponseEntity.ok(tiposLancamentos);
	}
}
