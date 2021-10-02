package br.com.alura.transacoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.modelo.Transacao;

@Transactional
@Repository
public class TransacaoDao {
	
	@Autowired
	private EntityManager em;

	public void salvar(Transacao transacao) {
		em.persist(transacao);
	}
	
	public List<Transacao>listar(){
		return em
				.createQuery("select t from Transacao t", Transacao.class)
				.getResultList();
				
	}
}
