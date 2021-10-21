package br.com.alura.transacoes.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoUsuarioFormDto extends UsuarioFormDto {
	@NotNull
	private Long id;

}
