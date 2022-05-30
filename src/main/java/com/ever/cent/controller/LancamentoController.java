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
import com.ever.cent.domain.dto.Resumo;
import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.service.impl.LancamentoServiceImpl;

@RestController
@RequestMapping("api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoServiceImpl service;

	@GetMapping("/{userID}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoByUserId(
			@PathVariable(value = "userID") String userID) {
		return new ResponseEntity<>(service.getLacamentos(Long.valueOf(userID)),
				HttpStatus.OK);
	}

	@GetMapping("/{userID}/registro/{lancamentoID}")
	public ResponseEntity<LancamentosResponseDTO> getLancamentoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "lancamentoID") String lancamentoID) {
		LancamentosResponseDTO lancamentoByID = service.getLancamentoByID(Long.valueOf(userID),
				Long.valueOf(lancamentoID));
		return new ResponseEntity<>(lancamentoByID, HttpStatus.OK);
	}


	@GetMapping("/{userID}/mes/{mes}/{ano}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano, @CurrentUser LocalUser user) {
		List<LancamentosResponseDTO> lancamentosByID = service.getLancamentosPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/{userID}/renda/{mes}/{ano}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoRendaByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano, @CurrentUser LocalUser user) {
		List<LancamentosResponseDTO> lancamentosByID = service.getLancamentosRendaPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/{userID}/gasto/{mes}/{ano}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoGastoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano, @CurrentUser LocalUser user) {
		List<LancamentosResponseDTO> lancamentosByID = service.getLancamentosGastoPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/{userID}/resumo/gasto/{mes}/{ano}")
	public ResponseEntity<List<Resumo>> getLancamentoResumoGastoByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano, @CurrentUser LocalUser user) {
		List<Resumo> lancamentosByID = service.getLancamentosGastoResumoPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/{userID}/resumo/renda/{mes}/{ano}")
	public ResponseEntity<List<Resumo>> getLancamentoResumoRendaByUserId(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano, @CurrentUser LocalUser user) {
		List<Resumo> lancamentosByID = service.getLancamentosRendaResumoPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@PostMapping("/{userID}")
	public ResponseEntity<LancamentosResponseDTO> postLancamento(@PathVariable(value = "userID") String userID,
			@RequestBody LancamentoRequestDTO lancamento) {
		LancamentosResponseDTO novoLancamento = service.saveNovoLancamento(Long.valueOf(userID), lancamento);
		return new ResponseEntity<>(novoLancamento, HttpStatus.CREATED);
	}

	@DeleteMapping("/{userID}/{lancamentoID}")
	public ResponseEntity<Object> deleteLancamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "lancamentoID") String lancamentoID) {
		service.deleteLancamentoByID(Long.valueOf(userID), Long.valueOf(lancamentoID));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/{userID}/{lancamentoID}")
	public ResponseEntity<LancamentosResponseDTO> patchLancamento(@PathVariable(value = "userID") String userID,
			@PathVariable(value = "lancamentoID") String lancamentoID, @RequestBody LancamentoRequestDTO lancamento) {
		LancamentosResponseDTO response = service.updateLancamento(Long.valueOf(userID), lancamento);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
