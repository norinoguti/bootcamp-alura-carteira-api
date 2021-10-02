package br.com.alura.transacoes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dto.TransacaoDto;
import br.com.alura.transacoes.dto.TransacaoFormDto;
import br.com.alura.transacoes.modelo.Transacao;
import br.com.alura.transacoes.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public List<TransacaoDto> listar() {
		List<Transacao> transacoes = transacaoRepository.findAll();	
		return transacoes.stream().map(t -> modelMapper.map(t, TransacaoDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(TransacaoFormDto dto) {
		Transacao transacao = modelMapper.map(dto, Transacao.class);
		transacao.setId(null);
		transacaoRepository.save(transacao);

	}
}
