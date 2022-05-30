package com.ever.cent.domain.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resumo {

    String tipo;

    BigDecimal valor;
}