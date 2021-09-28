package br.com.alura.transacoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.transacoes.modelo.TipoTransacao;
import br.com.alura.transacoes.modelo.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class TransacaoDto {
	private String ticker;
	private BigDecimal preco;
	private int quantidade;
	private TipoTransacao tipo;
	
	
	
}
