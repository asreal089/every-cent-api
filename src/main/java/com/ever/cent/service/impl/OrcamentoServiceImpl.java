package com.ever.cent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		OrcamentoResponseDTO response = convertToOrcamentoResponseDTO(savedEntity);
		return response;
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
	
	public ResponseEntity<OrcamentoResponseDTO> getOrcamentoById(Long userID, Long orcamentoID) {
		Optional<Orcamento> orcamento = orcamentoRepo.findById(orcamentoID);
		if(orcamento.isPresent()) {
			if(!(orcamento.get().user == null)) {				
				if(orcamento.get().user.getId() == userID) {
					OrcamentoResponseDTO response = convertToOrcamentoResponseDTO(orcamento.get());
					return new ResponseEntity<OrcamentoResponseDTO>(response, HttpStatus.OK);
				}
			}
			return new ResponseEntity<OrcamentoResponseDTO>(OrcamentoResponseDTO.builder().build(), HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<OrcamentoResponseDTO>(OrcamentoResponseDTO.builder().build(), HttpStatus.NOT_FOUND);
	}

	private OrcamentoResponseDTO convertToOrcamentoResponseDTO(Orcamento savedEntity) {
		OrcamentoResponseDTO response = OrcamentoResponseDTO.builder().id(savedEntity.getId())
				.tipo_id(savedEntity.getTipoLancamento().id).is_renda(savedEntity.getTipoLancamento().isRenda)
				.valor(savedEntity.getValor()).tipo(savedEntity.getTipoLancamento().tipo).build();
		return response;
	}

	public OrcamentoResponseDTO getOrcamentosByUserIdAndTipoID(Long user_id, Integer tipo_id) {
		Orcamento orcamento = orcamentoRepo.findByUserIdAndTipoLancamento_Id(user_id, tipo_id);
		OrcamentoResponseDTO response = convertToOrcamentoResponseDTO(orcamento);
		return response;
	}

	public ResponseEntity<String> delete(Long userID, Long orcamentoID) {
		Optional<Orcamento> orcamento = orcamentoRepo.findById(orcamentoID);
		if(orcamento.isPresent()){			
			if(orcamento.get().getUser().getId()== userID) {			
				orcamentoRepo.deleteById(orcamentoID);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> patch(Long userID, Long orcamentoID, OrcamentoRequestDTO orcamentoAtualizado) {
		Optional<Orcamento> orcamento = orcamentoRepo.findById(orcamentoID);
		Optional<TipoLancamento> tipo = tipoRepo.findById(orcamentoAtualizado.getTipo_id());
		if(orcamento.isPresent()&& tipo.isPresent()){			
			if(orcamento.get().getUser().getId()== userID) {
				orcamento.get().setTipoLancamento(tipo.get());
				orcamento.get().setValor(orcamentoAtualizado.getValor());
				orcamentoRepo.save(orcamento.get());
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
