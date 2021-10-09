package com.ever.cent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ever.cent.domain.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>  {
	List<Orcamento> findByUser(Long user_id);
}