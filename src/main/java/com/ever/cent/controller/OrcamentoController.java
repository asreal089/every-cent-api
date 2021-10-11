package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;
import com.ever.cent.service.impl.OrcamentoServiceImpl;

@RestController
@RequestMapping("api/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoServiceImpl orcamentoService;

	@GetMapping("/{userID}")
	public List<OrcamentoResponseDTO> getOrcamentoByUserId(@PathVariable(value = "userID") String userID) {
		return orcamentoService.getOrcamentosByUserId(Long.valueOf(userID));
	}

	@GetMapping("/{userID}/{tipoID}")
	public OrcamentoResponseDTO getOrcamentoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "tipoID") String tipoID) {
		return orcamentoService.getOrcamentosByUserIdAndTipoID(Long.valueOf(userID), Integer.valueOf(tipoID));
	}

	@PostMapping("/{userID}")
	public OrcamentoResponseDTO postOrcamento(@PathVariable(value = "userID") String userID,
			@RequestBody OrcamentoRequestDTO orcamento) {
		OrcamentoResponseDTO response = orcamentoService.novoOrcamento(Long.valueOf(userID), orcamento);
		return response;
	}
	
	@DeleteMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<String> deleteOrcamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		ResponseEntity<String> response = orcamentoService.delete(Long.valueOf(userID), Long.valueOf(orcamentoID));
		return response;
	}

}
