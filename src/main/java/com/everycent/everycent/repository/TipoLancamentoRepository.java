package com.everycent.everycent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everycent.everycent.model.TipoLancamento;

public interface TipoLancamentoRepository extends JpaRepository<TipoLancamento, Long> {
	
}
