package br.com.alura.transacoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.modelo.Usuario;
@Transactional
@Repository
public class UsuarioDao {
	
	@Autowired
	private EntityManager em;
	
	public void salvar(Usuario usuario ) {
		em.persist(usuario);
	}
	
	public List<Usuario>listar(){
		return em
				.createQuery("select u from Usuario u", Usuario.class)
				.getResultList();
	}

}
