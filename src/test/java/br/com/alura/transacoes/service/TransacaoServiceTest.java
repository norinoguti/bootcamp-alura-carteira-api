package br.com.alura.transacoes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.transacoes.dto.TransacaoDto;
import br.com.alura.transacoes.dto.TransacaoFormDto;
import br.com.alura.transacoes.modelo.TipoTransacao;
import br.com.alura.transacoes.repository.TransacaoRepository;
import br.com.alura.transacoes.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

	@Mock
	private TransacaoRepository transacaoRepository;

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private TransacaoService service;

	@Test
	void deveriaCadastrarUmaTransacao() {
		TransacaoFormDto formDto = new TransacaoFormDto("ITSA4", LocalDate.now(), new BigDecimal("10.45"), 10,
				TipoTransacao.COMPRA, 1l);

		TransacaoDto dto = service.cadastrar(formDto);
		assertEquals(formDto.getTicker(), dto.getTicker());
		assertEquals(formDto.getPreco(), dto.getPreco());
		assertEquals(formDto.getQuantidade(), dto.getQuantidade());
		assertEquals(formDto.getTipo(), dto.getTipo());
	}

	@Test
	void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {
		TransacaoFormDto formDto = new TransacaoFormDto("ITSA4", LocalDate.now(), new BigDecimal("10.45"), 10,
				TipoTransacao.COMPRA, 1L);

		Mockito
		.when(usuarioRepository
		.getById(formDto.getUsuarioId()))
		.thenThrow(EntityNotFoundException.class);

		assertThrows(IllegalArgumentException.class, () -> service.cadastrar(formDto));
	}

}
