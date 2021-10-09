package com.ever.cent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ever.cent.domain.model.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>  {

}
