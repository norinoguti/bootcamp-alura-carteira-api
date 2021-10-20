package br.com.alura.transacoes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.transacoes.modelo.TipoTransacao;
import br.com.alura.transacoes.modelo.Transacao;
import br.com.alura.transacoes.modelo.Usuario;

class CalculadoraDeImpostoServiceTest {

	private CalculadoraDeImpostoService calculadora;
	
	private Transacao criarTransacao(BigDecimal preco, Integer quantidade,TipoTransacao tipo) {
		Transacao transacao = new Transacao(
				1L,
				"MGLU23",
				LocalDate.now(),
				preco,
				quantidade,
				tipo,
				new Usuario(1L,"Floriano","nori@gmail.com","12456"));
		return transacao;
	}
	
	@BeforeEach //faz uma instancia no inicio de cada método de teste
	public void inicializar() {
		calculadora = new CalculadoraDeImpostoService();
	}
	
	
	@Test
	void transacaoDoTipoCompraNaoDeveCalcularImposto() {
		Transacao transacao = criarTransacao(new BigDecimal("30.00"),10,TipoTransacao.COMPRA);
				
		BigDecimal imposto = calculadora.calcularImposto(transacao);
		assertEquals(BigDecimal.ZERO, imposto);
	}

	
	
	@Test
	void transacaoDoTipoVendaComValorMenorDoQueVinteMilNaoDeveCalcularImposto() {
		Transacao transacao = criarTransacao(new BigDecimal("30.00"),10, TipoTransacao.VENDA);
				
		BigDecimal imposto = calculadora.calcularImposto(transacao);
		assertEquals(BigDecimal.ZERO, imposto);
	}
	@Test
	void transacaoDoTipoVendaComValorMaiorDoQueVinteMilDeveCalcularImposto() {
		Transacao transacao = criarTransacao(new BigDecimal("1000.00"), 30, TipoTransacao.VENDA);
			
		BigDecimal imposto = calculadora.calcularImposto(transacao);
		assertEquals(new BigDecimal("4500.00"), imposto);
	}
}
