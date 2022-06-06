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

import com.ever.cent.config.CurrentUser;
import com.ever.cent.domain.dto.LocalUser;
import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;
import com.ever.cent.service.impl.OrcamentoServiceImpl;

@RestController
@RequestMapping("api/orcamento")
public class OrcamentoController {

	@Autowired
	private OrcamentoServiceImpl orcamentoService;

	@GetMapping("/")
	public ResponseEntity<List<OrcamentoResponseDTO>> getOrcamentoByUserId(@CurrentUser LocalUser user) {
		return new ResponseEntity<>(orcamentoService.getOrcamentosByUserId(user.getUser().getId()), HttpStatus.OK);
	}

	@GetMapping("/{orcamentoID}")
	public ResponseEntity<OrcamentoResponseDTO> getOrcamentoByUserId(@CurrentUser LocalUser user,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		OrcamentoResponseDTO orcamentoById = orcamentoService.getOrcamentoById(user.getUser().getId(), Long.valueOf(orcamentoID));
		return new ResponseEntity<>(orcamentoById, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<OrcamentoResponseDTO> postOrcamento(@CurrentUser LocalUser user,
			@RequestBody OrcamentoRequestDTO orcamento) {
		OrcamentoResponseDTO novoOrcamento = orcamentoService.novoOrcamento(user.getUser().getId(), orcamento);
		return new ResponseEntity<>(novoOrcamento, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{orcamentoID}")
	public ResponseEntity<Object> deleteOrcamento(@CurrentUser LocalUser user,
			@PathVariable(value = "orcamentoID") String orcamentoID) {
		boolean deletado = orcamentoService.delete(user.getUser().getId(), Long.valueOf(orcamentoID));
		if(deletado) {			
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PatchMapping("/{orcamentoID}")
	public ResponseEntity<OrcamentoResponseDTO> patchOrcamento(@CurrentUser LocalUser user,
			@PathVariable(value = "orcamentoID") String orcamentoID, @RequestBody OrcamentoRequestDTO orcamento ) {
		OrcamentoResponseDTO patch = orcamentoService.patch(user.getUser().getId(), Long.valueOf(orcamentoID), orcamento);
		return new ResponseEntity<>(patch, HttpStatus.OK);
	}

}
