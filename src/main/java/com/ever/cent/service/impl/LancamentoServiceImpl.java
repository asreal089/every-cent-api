package com.ever.cent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ever.cent.domain.dto.Resumo;
import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.domain.model.Lancamento;
import com.ever.cent.domain.model.TipoLancamento;
import com.ever.cent.domain.model.User;
import com.ever.cent.exception.ForbidenAccessException;
import com.ever.cent.exception.NotFoundException;
import com.ever.cent.repository.LancamentoRepository;
import com.ever.cent.repository.TipoLancamentoRepository;
import com.ever.cent.repository.UserRepository;
import com.ever.cent.service.LancamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final String FORBIDEN = "Acesso negado ao lançamento.";

	private static final String NOT_FOUND = "Lancamento não encontrado.";

	@Autowired
	private LancamentoRepository repo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TipoLancamentoRepository tipoRepo;

	@Override
	public List<LancamentosResponseDTO> getLacamentos(Long userID) {
		List<Lancamento> lancamentos = repo.findByUserId(userID);
		return converterListaLancamentoToResponseDTO(lancamentos);
	}

	@Override
	public LancamentosResponseDTO getLancamentoByID(Long userID, Long lancamentoID) {
		Optional<Lancamento> lancamento = repo.findById(lancamentoID);
		if (!lancamento.isPresent()) {
			throw new NotFoundException(NOT_FOUND);
		}
		if(lancamento.get().getUser().getId() !=  userID) {
			throw new ForbidenAccessException(FORBIDEN);
		}
		return converLancamentoToResponseDTO(lancamento.get());
	}

	@Override
	public List<LancamentosResponseDTO> getLancamentosPorMes(Long userId, Integer mes, Integer ano){
		List<Lancamento> lancamentos = repo.getByUserYearAndMonth(userId, ano, mes);
		return converterListaLancamentoToResponseDTO(lancamentos);
	}

	@Override
	public List<LancamentosResponseDTO> getLancamentosRendaPorMes(Long userId, Integer mes, Integer ano){
		List<Lancamento> lancamentos = repo.getRendaByUserYearAndMonth(userId, ano, mes);
		return converterListaLancamentoToResponseDTO(lancamentos);
	}

	@Override
	public List<LancamentosResponseDTO> getLancamentosGastoPorMes(Long userId, Integer mes, Integer ano){
		List<Lancamento> lancamentos = repo.getGastoByUserYearAndMonth(userId, ano, mes);
		return converterListaLancamentoToResponseDTO(lancamentos);
	}

	@Override
	public List<Resumo> getLancamentosGastoResumoPorMes(Long userId, Integer mes, Integer ano){
		return repo.getResumoGastoByUserYearAndMonth(userId, ano, mes);
	}

	@Override
	public List<Resumo> getLancamentosRendaResumoPorMes(Long userId, Integer mes, Integer ano){
		return repo.getResumoRendaByUserYearAndMonth(userId, ano, mes);
	}

	@Override
	public LancamentosResponseDTO saveNovoLancamento(Long userID, LancamentoRequestDTO lancamento) {
		User user = userRepo.findById(userID).get();
		TipoLancamento tipo = tipoRepo.findById(lancamento.getTipoID()).get();
		Lancamento lancamentoEntidade = Lancamento.builder().user(user).tipoLancamento(tipo)
				.dataLancamento(lancamento.getData_lacamento()).descricao(lancamento.getDescricao())
				.valor(lancamento.getValor()).build();

		Lancamento entidadeSalva = repo.save(lancamentoEntidade);

		return converLancamentoToResponseDTO(entidadeSalva);
	}

	@Override
	public boolean deleteLancamentoByID(Long userID, Long lacamentoID) {
		Optional<Lancamento> entidade = repo.findById(lacamentoID);
		if(!entidade.isPresent()) {
			throw new NotFoundException(NOT_FOUND);
		}
		Lancamento lancamento = entidade.get();
		if(lancamento.getUser().getId() != userID) {
			throw new ForbidenAccessException(FORBIDEN);
		}
		repo.deleteById(lacamentoID);
		return true;
	}

	@Override
	public LancamentosResponseDTO updateLancamento(Long userID, LancamentoRequestDTO lancamento) {

			Optional<Lancamento> lancamentoEntitdade = repo.findById(lancamento.getLacamentoID());
			if(!lancamentoEntitdade.isPresent()) {
				throw new NotFoundException(NOT_FOUND);
			}
			Lancamento entidade = lancamentoEntitdade.get();
			
			if(entidade.getUser().getId() != userID) {
				throw new ForbidenAccessException(FORBIDEN);				
			}
			entidade.setDataLancamento(lancamento.getData_lacamento());
			entidade.setDescricao(lancamento.getDescricao());
			TipoLancamento tipo = tipoRepo.findById(lancamento.getTipoID()).get();
			entidade.setTipoLancamento(tipo);
			entidade.setValor(lancamento.getValor());
			
			return converLancamentoToResponseDTO(repo.save(entidade));
		
		
		
	}

	private List<LancamentosResponseDTO> converterListaLancamentoToResponseDTO(List<Lancamento> lancamentos) {
		List<LancamentosResponseDTO> response = new ArrayList<>();
		for (Lancamento lancamento : lancamentos) {
			response.add(converLancamentoToResponseDTO(lancamento));
		}
		return response;
	}

	private LancamentosResponseDTO converLancamentoToResponseDTO(Lancamento lancamento) {
		return LancamentosResponseDTO.builder().userID(lancamento.getUser().getId())
				.data_lacamento(lancamento.getDataLancamento()).lancamentoID(lancamento.getId())
				.tipoID(lancamento.getTipoLancamento().id).tipo(lancamento.getTipoLancamento().tipo)
				.descricao(lancamento.getDescricao()).valor(lancamento.getValor())
				.isRenda(lancamento.getTipoLancamento().isRenda).build();
	}

}
