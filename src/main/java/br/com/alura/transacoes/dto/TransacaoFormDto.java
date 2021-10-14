package br.com.alura.transacoes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.transacoes.modelo.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoFormDto {
	@NotBlank
	@Size(min=5, max=6)
	@Pattern(regexp = "[a-zA-Z]{4}[0-9][0-9]?", message = "{ticker.formato.invalido}")
	private String ticker;
	@PastOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;	
	@NotNull
	@DecimalMin("0.01")
	private BigDecimal preco;
	@NotNull
	@Min(10)
	private Integer quantidade;
	@NotNull
	private TipoTransacao tipo;
	@NotNull
	@JsonAlias("usuario_id")
	private Long usuarioId;
}
