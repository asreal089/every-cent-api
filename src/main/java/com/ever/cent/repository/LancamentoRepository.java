package com.ever.cent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ever.cent.domain.dto.Resumo;
import com.ever.cent.domain.model.Lancamento;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByUserId(Long user_id);

	@Query("select l from Lancamento l where l.user.id = :user_id and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month")
	List<Lancamento> getByUserYearAndMonth(@Param("user_id") Long user_id, @Param("year") int year, @Param("month") int month);

	@Query("select l from Lancamento l where l.user.id = :user_id and l.tipoLancamento.isRenda = false and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month")
	List<Lancamento> getGastoByUserYearAndMonth(@Param("user_id") Long user_id, @Param("year") int year, @Param("month") int month);

	@Query("select l from Lancamento l where l.user.id = :user_id and l.tipoLancamento.isRenda = true and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month")
	List<Lancamento> getRendaByUserYearAndMonth(@Param("user_id") Long user_id, @Param("year") int year, @Param("month") int month);

	@Query("select new com.ever.cent.domain.dto.Resumo(l.tipoLancamento.tipo, SUM(l.valor)) from Lancamento l where l.user.id = :user_id and l.tipoLancamento.isRenda = false and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month group by l.tipoLancamento.id, l.tipoLancamento.tipo")
	List<Resumo> getResumoGastoByUserYearAndMonth(@Param("user_id") Long user_id, @Param("year") int year, @Param("month") int month);

	@Query("select new com.ever.cent.domain.dto.Resumo(l.tipoLancamento.tipo, SUM(l.valor))  from Lancamento l where l.user.id = :user_id and l.tipoLancamento.isRenda = true and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month group by l.tipoLancamento.id, l.tipoLancamento.tipo")
	List<Resumo> getResumoRendaByUserYearAndMonth(@Param("user_id") Long user_id, @Param("year") int year, @Param("month") int month);


}
