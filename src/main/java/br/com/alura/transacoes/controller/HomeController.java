package br.com.alura.transacoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/home")
@Api(tags = "Home")
public class HomeController {
	
	@GetMapping
	public String home() {
		return "Deploy executado com sucesso!";
		
	}

}
