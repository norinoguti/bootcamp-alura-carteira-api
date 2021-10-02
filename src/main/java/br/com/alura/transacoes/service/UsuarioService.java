package br.com.alura.transacoes.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.transacoes.dto.UsuarioDto;
import br.com.alura.transacoes.dto.UsuarioFormDto;
import br.com.alura.transacoes.modelo.Usuario;
import br.com.alura.transacoes.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDto.class));

	}

	public void cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);

		// cria senha automaticamente
		String senha = new Random().nextInt(99999) + "";
		usuario.setSenha(senha);
		//System.out.println(usuario.getSenha());
		
		usuarioRepository.save(usuario);
	}
}
