package br.com.alura.transacoes.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDetalhadaDto extends TransacaoDto {
	
	private LocalDate data;
	private UsuarioDto usuario;
	
	
}
