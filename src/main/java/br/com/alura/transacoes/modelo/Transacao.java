package br.com.alura.transacoes.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"data", "valor", "tipo"})
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
	private String ticker;
	private LocalDate data;
	private BigDecimal valor;
	private int quantidade;
	private TipoTransacao tipo;

}
