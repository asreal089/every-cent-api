package com.ever.cent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ever.cent.domain.dto.Resumo;
import com.ever.cent.domain.dto.ResumoGastoOrcamento;
import com.ever.cent.domain.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	List<Lancamento> findByUserId(Long userId);

	@Query("select l from Lancamento l where l.user.id = :userId and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month")
	List<Lancamento> getByUserYearAndMonth(@Param("userId") Long userId, @Param("year") int year,
			@Param("month") int month);

	@Query("select l from Lancamento l where l.user.id = :userId and l.tipoLancamento.isRenda = false and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month")
	List<Lancamento> getGastoByUserYearAndMonth(@Param("userId") Long userId, @Param("year") int year,
			@Param("month") int month);

	@Query("select l from Lancamento l where l.user.id = :userId and l.tipoLancamento.isRenda = true and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month")
	List<Lancamento> getRendaByUserYearAndMonth(@Param("userId") Long userId, @Param("year") int year,
			@Param("month") int month);

	@Query("select new com.ever.cent.domain.dto.Resumo(l.tipoLancamento.tipo, SUM(l.valor)) from Lancamento l where l.user.id = :userId and l.tipoLancamento.isRenda = false and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month group by l.tipoLancamento.id, l.tipoLancamento.tipo")
	List<Resumo> getResumoGastoByUserYearAndMonth(@Param("userId") Long userId, @Param("year") int year,
			@Param("month") int month);

	@Query("select new com.ever.cent.domain.dto.Resumo(l.tipoLancamento.tipo, SUM(l.valor))  from Lancamento l where l.user.id = :userId and l.tipoLancamento.isRenda = true and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month group by l.tipoLancamento.id, l.tipoLancamento.tipo")
	List<Resumo> getResumoRendaByUserYearAndMonth(@Param("userId") Long userId, @Param("year") int year,
			@Param("month") int month);

	@Query("select new com.ever.cent.domain.dto.ResumoGastoOrcamento(l.tipoLancamento.tipo, SUM(l.valor), o.valor)  from Lancamento l left join Orcamento o on (l.user.id = o.user.id AND l.tipoLancamento.id = o.tipoLancamento.id) where l.user.id = :userId and l.tipoLancamento.isRenda = false and EXTRACT (year FROM l.dataLancamento) = :year and EXTRACT (month FROM l.dataLancamento) = :month group by l.tipoLancamento.id, l.tipoLancamento.tipo, o.valor")
	List<ResumoGastoOrcamento> getGastosVOrcamentoResumoPorMes(@Param("userId") Long userId, @Param("year") int year,
			@Param("month") int month);

}
