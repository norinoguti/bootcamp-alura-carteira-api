package br.com.alura.transacoes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.transacoes.dto.UsuarioDto;
import br.com.alura.transacoes.dto.UsuarioFormDto;
import br.com.alura.transacoes.modelo.Usuario;
import br.com.alura.transacoes.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
		
	@Autowired
	private UsuarioService service;
	@GetMapping
	public List<UsuarioDto>listar(){
		return service.listar();		
	}
	
	@PostMapping
	public void cadastrar(@RequestBody UsuarioFormDto dto) {
		service.cadastrar(dto);
	}
}
