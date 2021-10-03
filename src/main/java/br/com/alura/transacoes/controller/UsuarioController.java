package br.com.alura.transacoes.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.transacoes.dto.UsuarioDto;
import br.com.alura.transacoes.dto.UsuarioFormDto;
import br.com.alura.transacoes.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
		
	@Autowired
	private UsuarioService service;
	@GetMapping
	public Page<UsuarioDto>listar(@PageableDefault(size=10)Pageable paginacao){
		return service.listar(paginacao);		
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioFormDto dto,
			UriComponentsBuilder uriBuilder) {
		UsuarioDto usuarioDto = service.cadastrar(dto);
		
		URI uri = uriBuilder
				.path("/usuarios/{id}")
				.buildAndExpand(usuarioDto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(usuarioDto);
	}
}
