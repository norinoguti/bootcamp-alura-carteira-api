package br.com.alura.transacoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.transacoes.dto.ItemCarteiraDto;
import br.com.alura.transacoes.modelo.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
	@Query(" select new br.com.alura.transacoes.dto.ItemCarteiraDto("
			+ "t.ticker,"
			+ "sum(t.quantidade),"
			+ "sum(t.quantidade)* 1.0 /(select sum(t2.quantidade)from Transacao t2)* 100.0) "
			+ "from Transacao t"
			+ " group by t.ticker")
	 List<ItemCarteiraDto> relatorioCarteiraDeInvestimentos();
}
