package com.ever.cent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ever.cent.domain.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>  {
	
	List<Orcamento> findByUserId(Long userId);
	
	Optional<Orcamento> findByUserIdAndTipoLancamento_Id(Long userId, Integer tipoId);
	
	void deleteById(Long orcamentoId);
	
}
