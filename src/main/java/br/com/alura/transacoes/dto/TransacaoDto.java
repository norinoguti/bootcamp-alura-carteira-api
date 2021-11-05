package br.com.alura.transacoes.dto;

import java.math.BigDecimal;

import br.com.alura.transacoes.modelo.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDto {
	private Long id;
	private String ticker;
	private BigDecimal preco;
	private Integer quantidade;
	private TipoTransacao tipo;
	
	
	
}
