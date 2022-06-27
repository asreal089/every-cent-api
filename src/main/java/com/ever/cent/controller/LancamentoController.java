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
import com.ever.cent.domain.dto.ResumoGastoOrcamento;
import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.service.impl.LancamentoServiceImpl;

@RestController
@RequestMapping("api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoServiceImpl service;

	@GetMapping("/")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoByUserId(
			@CurrentUser LocalUser user) {
		return new ResponseEntity<>(service.getLacamentos(user.getUser().getId()),
				HttpStatus.OK);
	}

	@GetMapping("/registro/{lancamentoID}")
	public ResponseEntity<LancamentosResponseDTO> getLancamentoByUserId(
			@PathVariable(value = "lancamentoID") String lancamentoID, @CurrentUser LocalUser user) {
		LancamentosResponseDTO lancamentoByID = service.getLancamentoByID(user.getUser().getId(),
				Long.valueOf(lancamentoID));
		return new ResponseEntity<>(lancamentoByID, HttpStatus.OK);
	}

	@GetMapping("/mes/{mes}/{ano}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoByUserId(
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano,
			@CurrentUser LocalUser user) {
		List<LancamentosResponseDTO> lancamentosByID = service.getLancamentosPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/renda/{mes}/{ano}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoRendaByUserId(
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano,
			@CurrentUser LocalUser user) {
		List<LancamentosResponseDTO> lancamentosByID = service.getLancamentosRendaPorMes(user.getUser().getId(), mes,
				ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/gasto/{mes}/{ano}")
	public ResponseEntity<List<LancamentosResponseDTO>> getLancamentoGastoByUserId(
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano,
			@CurrentUser LocalUser user) {
		List<LancamentosResponseDTO> lancamentosByID = service.getLancamentosGastoPorMes(user.getUser().getId(), mes,
				ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/resumo/gasto/{mes}/{ano}")
	public ResponseEntity<List<Resumo>> getLancamentoResumoGastoByUserId(
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano,
			@CurrentUser LocalUser user) {
		List<Resumo> lancamentosByID = service.getLancamentosGastoResumoPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/resumo/renda/{mes}/{ano}")
	public ResponseEntity<List<Resumo>> getLancamentoResumoRendaByUserId(
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano,
			@CurrentUser LocalUser user) {
		List<Resumo> lancamentosByID = service.getLancamentosRendaResumoPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@GetMapping("/resumo/gasto-orcamento/{mes}/{ano}")
	public ResponseEntity<List<ResumoGastoOrcamento>> getGastosVOrcamentoResumoByUserId(
			@PathVariable(value = "mes") Integer mes, @PathVariable(value = "ano") Integer ano,
			@CurrentUser LocalUser user) {
		List<ResumoGastoOrcamento> lancamentosByID = service.getGastosVOrcamentoResumoPorMes(user.getUser().getId(), mes, ano);
		return new ResponseEntity<>(lancamentosByID, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<LancamentosResponseDTO> postLancamento(@CurrentUser LocalUser user,
			@RequestBody LancamentoRequestDTO lancamento) {
		LancamentosResponseDTO novoLancamento = service.saveNovoLancamento(user.getUser().getId(), lancamento);
		return new ResponseEntity<>(novoLancamento, HttpStatus.CREATED);
	}

	@DeleteMapping("/{lancamentoID}")
	public ResponseEntity<Object> deleteLancamento(@CurrentUser LocalUser user,
			@PathVariable(value = "lancamentoID") String lancamentoID) {
		service.deleteLancamentoByID(user.getUser().getId(), Long.valueOf(lancamentoID));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/{lancamentoID}")
	public ResponseEntity<LancamentosResponseDTO> patchLancamento(@CurrentUser LocalUser user,
			@PathVariable(value = "lancamentoID") String lancamentoID, @RequestBody LancamentoRequestDTO lancamento) {
		LancamentosResponseDTO response = service.updateLancamento(user.getUser().getId(), lancamento);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
