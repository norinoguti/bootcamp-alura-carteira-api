package br.com.alura.transacoes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dao.TransacaoDao;
import br.com.alura.transacoes.dto.TransacaoDto;
import br.com.alura.transacoes.dto.TransacaoFormDto;
import br.com.alura.transacoes.modelo.Transacao;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoDao transacaoDao;
	private ModelMapper modelMapper = new ModelMapper();

	public List<TransacaoDto> listar() {
		List<Transacao> transacoes = transacaoDao.listar();	
		return transacoes.stream().map(t -> modelMapper.map(t, TransacaoDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(TransacaoFormDto dto) {
		Transacao transacao = modelMapper.map(dto, Transacao.class);
		transacaoDao.salvar(transacao);

	}
}
