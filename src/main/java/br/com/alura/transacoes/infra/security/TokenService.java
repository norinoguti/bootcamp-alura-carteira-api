package br.com.alura.transacoes.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.modelo.Usuario;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {
	
	@Value("${jjwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		return Jwts
				.builder()
				.setId(logado.getId().toString())
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secret")//método de criptografia e senha para gerar criptografia
				.compact();
	}

}
