package br.com.alura.transacoes.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import javax.swing.text.AbstractDocument.Content;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")//executar utilizando database de teste
@Transactional //para fazer o rollback após executar o teste
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	void naoDeveriaCadastrarUsuarioComDadosIncompletos() throws Exception {
		String json = "{}";
		
		mvc.perform(
				post("/usuarios") //método pertence a biblioteca MockMvcRequestBuilders
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest());//método status pertence a biblioteca MockMvcResultMatchers
	}
	
	@Test
	void deveriaCadastrarUsuarioComDadosCompletos() throws Exception {
		String json = "{\"nome\":\"fulano\",\"login\":\"fulano@email.com\"}";
		
		mvc.perform(
				post("/usuarios") //método pertence a biblioteca MockMvcRequestBuilders
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())//método status pertence a biblioteca MockMvcResultMatchers
				.andExpect(header().exists("Location"))
				.andExpect(content().json(json));
	}

}
