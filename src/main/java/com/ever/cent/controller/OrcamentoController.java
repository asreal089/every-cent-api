package com.ever.cent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.domain.model.Orcamento;
import com.ever.cent.domain.model.TipoLancamento;
import com.ever.cent.domain.model.User;
import com.ever.cent.repository.OrcamentoRepository;
import com.ever.cent.repository.TipoLancamentoRepository;
import com.ever.cent.repository.UserRepository;

@RestController
@RequestMapping("api/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoRepository orcamentoRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TipoLancamentoRepository tipoRepo;

	@GetMapping("/{userID}")
	public List<Orcamento> getOrcamentoByUserId(@PathVariable(value = "userID") Long userID) {
		return orcamentoRepo.findByUser(userID);
	}

	@PostMapping("/{userID}/{tipoID}")
	public Orcamento postOrcamento(@PathVariable(value = "userID") String userID, @PathVariable(value = "tipoID") String tipoID, @RequestBody Orcamento orcamento) {
		Optional<User> usuario = userRepo.findById(Long.valueOf(userID));
		Optional<TipoLancamento> tipo= tipoRepo.findById(Integer.valueOf(tipoID));
		orcamento.setUser(usuario.get());
		orcamento.setTipoLancamento(tipo.get());
		Orcamento savedEntity = orcamentoRepo.save(orcamento);
		return savedEntity;
	}
}
