package com.ever.cent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ever.cent.service.impl.LancamentoServiceImpl;

@RestController
@RequestMapping("api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoServiceImpl service;
}
