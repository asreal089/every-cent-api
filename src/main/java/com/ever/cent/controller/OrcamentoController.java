package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	@GetMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<OrcamentoResponseDTO> getOrcamentoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		return orcamentoService.getOrcamentoById(Long.valueOf(userID), Long.valueOf(orcamentoID));
	}

	@PostMapping("/{userID}")
	public ResponseEntity<String> postOrcamento(@PathVariable(value = "userID") String userID,
			@RequestBody OrcamentoRequestDTO orcamento) {
		return orcamentoService.novoOrcamento(Long.valueOf(userID), orcamento);
	}
	
	@DeleteMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<String> deleteOrcamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		ResponseEntity<String> response = orcamentoService.delete(Long.valueOf(userID), Long.valueOf(orcamentoID));
		return response;
	}
	
	@PatchMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<String> patchOrcamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID, @RequestBody OrcamentoRequestDTO orcamento ) {
		ResponseEntity<String> response = orcamentoService.patch(Long.valueOf(userID), Long.valueOf(orcamentoID), orcamento);
		return response;
	}

}
