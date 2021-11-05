package br.com.alura.transacoes.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.transacoes.dto.ItemCarteiraDto;
import br.com.alura.transacoes.modelo.Transacao;
import br.com.alura.transacoes.modelo.Usuario;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
	@Query("SELECT "
			+ "new br.com.alura.transacoes.dto.ItemCarteiraDto("
			+ "t.ticker,"
			+ "SUM(CASE WHEN(t.tipo='COMPRA') THEN t.quantidade ELSE (t.quantidade * -1)END),"
			+ "(SELECT SUM(CASE WHEN(t2.tipo='COMPRA') THEN t2.quantidade ELSE (t2.quantidade * -1)END)FROM Transacao t2)) "
			+ "FROM Transacao t GROUP BY t.ticker")
	 List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos();

	Page<Transacao> findAllByUsuario(Pageable paginacao, Usuario usuario);
}

//Query-> retorna ticker,
//quantidade por ticker fazendo o decremento quando é tipo venda e
//quantidade total de ticker fazendo o decremento quando é tipo venda