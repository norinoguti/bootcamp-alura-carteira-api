package br.com.alura.transacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TransacoesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacoesApiApplication.class, args);
	}

}
