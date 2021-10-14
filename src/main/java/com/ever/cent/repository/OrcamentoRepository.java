package com.ever.cent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ever.cent.domain.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>  {
	
	List<Orcamento> findByUserId(Long user_id);
	
	Optional<Orcamento> findByUserIdAndTipoLancamento_Id(Long user_id, Integer tipo_id);
	
	void deleteById(Long orcamento_id);
	
}
