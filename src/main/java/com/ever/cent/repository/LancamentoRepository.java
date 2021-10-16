package com.ever.cent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ever.cent.domain.model.Lancamento;
import com.ever.cent.domain.model.Orcamento;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByUserId(Long user_id);

	
}
