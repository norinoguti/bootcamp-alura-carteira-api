package br.com.alura.transacoes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.transacoes.dto.UsuarioDto;
import br.com.alura.transacoes.dto.UsuarioFormDto;
import br.com.alura.transacoes.modelo.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private List<Usuario> usuarios = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping
	public List<UsuarioDto>listar(){
		return usuarios
				.stream()
				.map(t-> modelMapper.map(t, UsuarioDto.class))
				.collect(Collectors.toList());
		
	}
	@PostMapping
	public void cadastrar(@RequestBody UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuarios.add(usuario);
	}
}
