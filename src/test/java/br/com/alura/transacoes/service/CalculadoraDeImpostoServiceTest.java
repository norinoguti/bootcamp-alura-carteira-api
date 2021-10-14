package br.com.alura.transacoes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.transacoes.modelo.TipoTransacao;
import br.com.alura.transacoes.modelo.Transacao;
import br.com.alura.transacoes.modelo.Usuario;

class CalculadoraDeImpostoServiceTest {

	@Test
	void transacaoDoTipoCompraNaoDeveCalcularImposto() {
		Transacao transacao = new Transacao(
				1L,
				"MGLU23",
				LocalDate.now(),
				new BigDecimal("30.00"),
				10,
				TipoTransacao.COMPRA,
				new Usuario(1L,"Floriano","nori@gmail.com","12456"));
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		BigDecimal imposto = calculadora.calcularImposto(transacao);
		assertEquals(BigDecimal.ZERO, imposto);
	}
	
	@Test
	void transacaoDoTipoVendaComValorMenorDoQueVinteMilNaoDeveCalcularImposto() {
		Transacao transacao = new Transacao(
				1L,
				"MGLU23",
				LocalDate.now(),
				new BigDecimal("30.00"),
				10,
				TipoTransacao.VENDA,
				new Usuario(1L,"Floriano","nori@gmail.com","12456"));
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		BigDecimal imposto = calculadora.calcularImposto(transacao);
		assertEquals(BigDecimal.ZERO, imposto);
	}
	@Test
	void transacaoDoTipoVendaComValorMaiorDoQueVinteMilDeveCalcularImposto() {
		Transacao transacao = new Transacao(
				1L,
				"MGLU23",
				LocalDate.now(),
				new BigDecimal("3000.00"),
				10,
				TipoTransacao.VENDA,
				new Usuario(1L,"Floriano","nori@gmail.com","12456"));
		CalculadoraDeImpostoService calculadora = new CalculadoraDeImpostoService();
		BigDecimal imposto = calculadora.calcularImposto(transacao);
		assertEquals(new BigDecimal("4500.00"), imposto);
	}
}
