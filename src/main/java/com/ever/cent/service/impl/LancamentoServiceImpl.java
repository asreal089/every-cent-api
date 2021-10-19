package com.ever.cent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ever.cent.domain.dto.lacamento.LancamentoRequestDTO;
import com.ever.cent.domain.dto.lacamento.LancamentosResponseDTO;
import com.ever.cent.domain.model.Lancamento;
import com.ever.cent.domain.model.TipoLancamento;
import com.ever.cent.domain.model.User;
import com.ever.cent.repository.LancamentoRepository;
import com.ever.cent.repository.TipoLancamentoRepository;
import com.ever.cent.repository.UserRepository;
import com.ever.cent.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	private LancamentoRepository repo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TipoLancamentoRepository tipoRepo;

	@Override
	public List<LancamentosResponseDTO> getLacamentos(Long userID) {
		List<Lancamento> lancamentos = repo.findByUserId(userID);
		List<LancamentosResponseDTO> response = new ArrayList<>();
		for (Lancamento lancamento : lancamentos) {
			response.add(converLancamentoToResponseDTO(lancamento));
		}
		return response;
	}

	@Override
	public LancamentosResponseDTO getLancamentoByID(Long userID, Long lancamentoID) {
		Optional<Lancamento> lancamento = repo.findById(lancamentoID);
		if (lancamento.isPresent()) {
			return converLancamentoToResponseDTO(lancamento.get());
		}
		return null;
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
		repo.deleteById(lacamentoID);
		return true;
	}

	@Override
	public LancamentosResponseDTO updateLancamento(Long userID, LancamentoRequestDTO lancamento) {
		Lancamento entidade = repo.findById(lancamento.getLacamentoID()).get();
		entidade.setDataLancamento(lancamento.getData_lacamento());
		entidade.setDescricao(lancamento.getDescricao());
		TipoLancamento tipo = tipoRepo.findById(lancamento.getTipoID()).get();
		entidade.setTipoLancamento(tipo);
		entidade.setValor(lancamento.getValor());

		return converLancamentoToResponseDTO(repo.save(entidade));
	}

	private LancamentosResponseDTO converLancamentoToResponseDTO(Lancamento lancamento) {
		return LancamentosResponseDTO.builder().userID(lancamento.getUser().getId())
				.data_lacamento(lancamento.getDataLancamento()).lacamentoID(lancamento.getId())
				.tipoID(lancamento.getTipoLancamento().id).tipo(lancamento.getTipoLancamento().tipo)
				.descricao(lancamento.getDescricao()).valor(lancamento.getValor())
				.isRenda(lancamento.getTipoLancamento().isRenda).build();
	}

}
