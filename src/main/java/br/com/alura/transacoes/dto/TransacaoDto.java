package br.com.alura.transacoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.transacoes.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDto {
	private Long id;
	private String ticker;
	private BigDecimal preco;
	private Integer quantidade;
	private TipoTransacao tipo;
	
	
	
}
