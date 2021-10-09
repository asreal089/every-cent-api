package com.ever.cent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ever.cent.domain.model.Lancamento;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
