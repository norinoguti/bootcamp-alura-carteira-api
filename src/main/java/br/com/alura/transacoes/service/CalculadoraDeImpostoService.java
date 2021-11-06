package br.com.alura.transacoes.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import br.com.alura.transacoes.modelo.TipoTransacao;
import br.com.alura.transacoes.modelo.Transacao;

@Service
public class CalculadoraDeImpostoService {
	
	//15% de imposto quando for tipo VENDA e valorTransacao for maior que 20.000,00
	public BigDecimal calcularImposto(Transacao transacao) {
		if (transacao.getTipo() == TipoTransacao.COMPRA) {
			return BigDecimal.ZERO;
		}
		BigDecimal valorTransacao = transacao
				.getPreco().multiply(new BigDecimal(transacao.getQuantidade()));
		//compareTo retorna 0 qdo iguais, 1 quando for maior e -1 quando for menor
		if(valorTransacao.compareTo(new BigDecimal(20000))<0) {
			return BigDecimal.ZERO;
		}
		else {
			return valorTransacao
					.multiply(new BigDecimal("0.15"))
					.setScale(2, RoundingMode.HALF_UP);
		}
	}

}
