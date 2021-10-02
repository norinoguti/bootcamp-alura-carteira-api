package br.com.alura.transacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.transacoes.modelo.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
	
}
