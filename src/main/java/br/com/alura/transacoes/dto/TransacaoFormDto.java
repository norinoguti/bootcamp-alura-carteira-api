package br.com.alura.transacoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.transacoes.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoFormDto {
	@NotBlank
	@Size(min=5,max=6)
	private String ticker;
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate data;
	@NotBlank
	@DecimalMin("0.01")
	private BigDecimal valor;
	@NotBlank
	private int quantidade;
	@NotBlank
	private TipoTransacao tipo;

}
