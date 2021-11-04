package br.com.alura.transacoes.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

	@Value("${jjwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		return Jwts.builder().setSubject(logado.getId().toString())
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)// método de criptografia e senha para gerar
																			// criptografia
				.compact();
	}

	public boolean isValido(String token) {
		try {
			Jwts
			.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long extrairIdUsuario(String token) {
		Claims claims = Jwts
		.parser()
		.setSigningKey(secret)
		.parseClaimsJws(token)
		.getBody();
	return Long.parseLong(claims.getSubject());
	}
}
