package com.ever.cent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ever.cent.domain.dto.orcamento.OrcamentoRequestDTO;
import com.ever.cent.domain.dto.orcamento.OrcamentoResponseDTO;
import com.ever.cent.domain.model.Orcamento;
import com.ever.cent.domain.model.TipoLancamento;
import com.ever.cent.domain.model.User;
import com.ever.cent.exception.ForbidenAccessException;
import com.ever.cent.exception.NotFoundException;
import com.ever.cent.repository.OrcamentoRepository;
import com.ever.cent.repository.TipoLancamentoRepository;
import com.ever.cent.repository.UserRepository;
import com.ever.cent.service.OrcamentoService;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

	private static final String FORBIDEN = "Acesso negado ao orçamento";

	private static final String NOT_FOUND = "Orçamento não encontrado";

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
		Optional<Orcamento> entidadeExistente =  getOrcamentosByUserIdAndTipoID(userID, orcamento.getTipo_id());
		if(entidadeExistente.isPresent()) {
			return patch(userID, entidadeExistente.get().getId(), orcamento);
		}
		Orcamento novoOrcamento = Orcamento.builder().user(usuario.get()).tipoLancamento(tipo.get())
				.valor(orcamento.getValor()).build();
		orcamentoRepo.save(novoOrcamento);
		return convertToOrcamentoResponseDTO(novoOrcamento);
	}

	@Override
	public List<OrcamentoResponseDTO> getOrcamentosByUserId(Long userID) {
		List<Orcamento> orcamentos = orcamentoRepo.findByUserId(userID);
		List<OrcamentoResponseDTO> response = new ArrayList<>();
		for (Orcamento orcamento : orcamentos) {
			response.add(convertToOrcamentoResponseDTO(orcamento));
		}
		return response;
	}
	
	public OrcamentoResponseDTO getOrcamentoById(Long userID, Long orcamentoID) {
		Optional<Orcamento> orcamento = orcamentoRepo.findById(orcamentoID);
		if(orcamento.isPresent()) {
			if(!(orcamento.get().user == null)) {				
				if(orcamento.get().user.getId() == userID) {
					OrcamentoResponseDTO response = convertToOrcamentoResponseDTO(orcamento.get());
					return response;
				}
			}
			throw new ForbidenAccessException(FORBIDEN);
		}
		throw new NotFoundException(NOT_FOUND);
	}

	private OrcamentoResponseDTO convertToOrcamentoResponseDTO(Orcamento savedEntity) {
		OrcamentoResponseDTO response = OrcamentoResponseDTO.builder().id(savedEntity.getId())
				.tipo_id(savedEntity.getTipoLancamento().id).is_renda(savedEntity.getTipoLancamento().isRenda)
				.valor(savedEntity.getValor()).tipo(savedEntity.getTipoLancamento().tipo).build();
		return response;
	}

	public Optional<Orcamento> getOrcamentosByUserIdAndTipoID(Long user_id, Integer tipo_id) {
		Optional<Orcamento> orcamento = orcamentoRepo.findByUserIdAndTipoLancamento_Id(user_id, tipo_id);
		return orcamento;
	}

	public Boolean delete(Long userID, Long orcamentoID) {
		Optional<Orcamento> orcamento = orcamentoRepo.findById(orcamentoID);
		if(orcamento.isPresent()){			
			if(orcamento.get().getUser().getId()== userID) {			
				orcamentoRepo.deleteById(orcamentoID);
				return true;
			}
			throw new ForbidenAccessException(FORBIDEN);
		}
		throw new NotFoundException(NOT_FOUND);
	}
	
	public OrcamentoResponseDTO patch(Long userID, Long orcamentoID, OrcamentoRequestDTO orcamentoAtualizado) {
		Optional<Orcamento> orcamento = orcamentoRepo.findById(orcamentoID);
		Optional<TipoLancamento> tipo = tipoRepo.findById(orcamentoAtualizado.getTipo_id());
		
		if(orcamento.isPresent()&& tipo.isPresent()){
			Optional<Orcamento>orcamentoSemelhante = getOrcamentosByUserIdAndTipoID(userID, orcamentoAtualizado.getTipo_id());
			if(orcamento.get().getUser().getId()== userID) {
				if(orcamentoSemelhante.isPresent()) {
					delete(userID, orcamentoSemelhante.get().getId());
				}
				orcamento.get().setTipoLancamento(tipo.get());
				orcamento.get().setValor(orcamentoAtualizado.getValor());
				orcamentoRepo.save(orcamento.get());
				return convertToOrcamentoResponseDTO(orcamento.get());
			}
			throw new ForbidenAccessException(FORBIDEN);
		}
		throw new NotFoundException(NOT_FOUND);
	}
}
