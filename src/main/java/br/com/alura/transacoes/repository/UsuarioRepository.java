package br.com.alura.transacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.transacoes.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
}