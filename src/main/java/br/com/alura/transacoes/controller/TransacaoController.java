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

import br.com.alura.transacoes.dto.TransacaoDto;
import br.com.alura.transacoes.dto.TransacaoFormDto;
import br.com.alura.transacoes.modelo.Transacao;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	private List<Transacao> transacoes = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	@GetMapping
	public List<TransacaoDto> listar() {
		return transacoes
				.stream()
				.map(t-> modelMapper.map(t, TransacaoDto.class))
				.collect(Collectors.toList());
		
	}
		
	@PostMapping
	public void cadastrar(@RequestBody TransacaoFormDto dto) {
		Transacao transacao = modelMapper.map(dto,Transacao.class);
		transacoes.add(transacao);
	}

}
