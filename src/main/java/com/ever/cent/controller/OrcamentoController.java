package com.ever.cent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<List<OrcamentoResponseDTO>> getOrcamentoByUserId(@PathVariable(value = "userID") String userID) {
		return new ResponseEntity<List<OrcamentoResponseDTO>>(orcamentoService.getOrcamentosByUserId(Long.valueOf(userID)), HttpStatus.OK);
	}

	@GetMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<OrcamentoResponseDTO> getOrcamentoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		OrcamentoResponseDTO orcamentoById = orcamentoService.getOrcamentoById(Long.valueOf(userID), Long.valueOf(orcamentoID));
		return new ResponseEntity<OrcamentoResponseDTO>(orcamentoById, HttpStatus.OK);
	}

	@PostMapping("/{userID}")
	public ResponseEntity<OrcamentoResponseDTO> postOrcamento(@PathVariable(value = "userID") String userID,
			@RequestBody OrcamentoRequestDTO orcamento) {
		OrcamentoResponseDTO novoOrcamento = orcamentoService.novoOrcamento(Long.valueOf(userID), orcamento);
		return new ResponseEntity<OrcamentoResponseDTO>(novoOrcamento, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<Object> deleteOrcamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		boolean deletado = orcamentoService.delete(Long.valueOf(userID), Long.valueOf(orcamentoID));
		if(deletado) {			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PatchMapping("/{userID}/{orcamentoID}")
	public ResponseEntity<OrcamentoResponseDTO> patchOrcamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "orcamentoID") String orcamentoID, @RequestBody OrcamentoRequestDTO orcamento ) {
		OrcamentoResponseDTO patch = orcamentoService.patch(Long.valueOf(userID), Long.valueOf(orcamentoID), orcamento);
		return new ResponseEntity<OrcamentoResponseDTO>(patch, HttpStatus.OK);
	}

}
