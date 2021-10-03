package br.com.alura.transacoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dto.ItemCarteiraDto;
import br.com.alura.transacoes.repository.TransacaoRepository;

@Service
public class RelatorioService {

	@Autowired
	private TransacaoRepository repository;
	
	public List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos() {
		return repository.relatorioCarteiraDeInvestimentos();
	}

}
