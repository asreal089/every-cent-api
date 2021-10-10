package com.ever.cent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;
import com.ever.cent.domain.model.Orcamento;
import com.ever.cent.domain.model.TipoLancamento;
import com.ever.cent.domain.model.User;
import com.ever.cent.repository.OrcamentoRepository;
import com.ever.cent.repository.TipoLancamentoRepository;
import com.ever.cent.repository.UserRepository;
import com.ever.cent.service.OrcamentoService;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

	@Autowired
	private OrcamentoRepository orcamentoRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TipoLancamentoRepository tipoRepo;

	@Override
	public OrcamentoResponseDTO novoOrcamento(Long userID, OrcamentoRequestDTO orcamento) {
		Optional<User> usuario = userRepo.findById(Long.valueOf(userID));
		Optional<TipoLancamento> tipo = tipoRepo.findById(Integer.valueOf(orcamento.getTipo_id()));
		Orcamento novoOrcamento = Orcamento.builder().user(usuario.get()).tipoLancamento(tipo.get())
				.valor(orcamento.getValor()).build();
		Orcamento savedEntity = orcamentoRepo.save(novoOrcamento);
		OrcamentoResponseDTO response = OrcamentoResponseDTO.builder().id(savedEntity.getId())
				.tipo_id(savedEntity.getTipoLancamento().id).is_renda(savedEntity.getTipoLancamento().isRenda)
				.valor(savedEntity.getValor()).tipo(savedEntity.getTipoLancamento().tipo).build();
		return response;
	}

	@Override
	public List<OrcamentoResponseDTO> getOrcamentosByUserId() {
		// TODO Auto-generated method stub
		return null;
	}
}
