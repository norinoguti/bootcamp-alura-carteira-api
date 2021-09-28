package br.com.alura.transacoes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
	@NotBlank
	@Size(min=5, max= 20)
	private String nome;
	@NotBlank
	@Size(min=5, max=10)
	private String login;
	

}
